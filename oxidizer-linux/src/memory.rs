use std::collections::HashMap;
use crate::object;
use crate::object::{ObjRef, Object};
use std::mem::size_of;
use std::process::exit;
use std::marker::PhantomData;

#[repr(C)]
pub struct OxObjInt {
    header: AllocHeader,
    data: i64
}

#[repr(C)]
pub struct OxObjFloat {
    header: AllocHeader,
    data: f64
}

#[repr(C)]
pub struct OxObjObject<'heap> {
    header: AllocHeader,
    data: object::Object<'heap>
}

pub enum AllocType {
    Int,
    Float,
    Object,
    String,
}

#[repr(C)]
pub struct AllocHeader {
    reference_count: u32,
    size: u32,
    padding_after: u8,
    mem_type: AllocType,
}

pub struct OxAllocator {
    top: usize,
    free: usize,
    allocated_count: usize,
}

pub struct Heap<'heap> {
    memory: Vec<u8>,
    allocator: OxAllocator,
    phantom: PhantomData<&'heap u8> //
}

struct AllocLayout {
    ptr: usize,
    amount: usize,
    padding: u8,
}

impl <'heap> Heap<'heap> {

    pub fn new() -> Heap<'heap> {
        let mut memory = Vec::new();
        memory.resize_with(4096, || 0);
        Heap {
            memory,
            allocator: OxAllocator {
                top: 0,
                free: 4096,
                allocated_count: 0
            },
            phantom: PhantomData
        }
    }

    pub fn allocate_str(&mut self, value: &str) -> ObjRef<'heap> {
        let amount = value.len() + size_of::<AllocHeader>();
        if !self.allocator.is_space_available(&self.memory, amount) {
            //self.compactify_memory();

            if !self.allocator.is_space_available(&self.memory, amount) {
                self.expand_memory();
            }
        }
        self.allocator.allocated_count += 1;
        let layout = self.allocator.allocate(amount);
        let ptr = layout.ptr;
        unsafe {
            let header = self.leak_header(layout.ptr);
            (*header).size = amount as u32;
            (*header).mem_type = AllocType::String;
            (*header).padding_after = layout.padding;
            (*header).reference_count = 1;
            let mut str_target = &mut self.memory[ptr+size_of::<AllocHeader>() .. ptr+size_of::<AllocHeader>() + value.len()];

            for (index, byte) in value.as_bytes().iter().enumerate() {
                str_target[index] = byte.clone();
            }

            let empty_pt = &str_target[0] as *const u8;

            match std::str::from_utf8(std::slice::from_raw_parts(empty_pt, str_target.len())) {
                Ok(reference) => ObjRef::String(Some(&mut *header as &mut AllocHeader), reference),
                Err(_) => exit(-1),
            }
        }
    }

    unsafe fn leak_header(&mut self, begin: usize) -> *mut AllocHeader {
        let mut header_target = &mut self.memory[begin..begin+size_of::<AllocHeader>()];
        let (_, body, _) = header_target.align_to_mut::<AllocHeader>();
        let header = &mut body[0] as *mut AllocHeader;
        header
    }

    pub fn allocate(&mut self, kind: AllocType) -> ObjRef<'heap> {
        let amount = match kind {
            AllocType::Int => size_of::<OxObjInt>(),
            AllocType::Float => size_of::<OxObjFloat>(),
            AllocType::Object => size_of::<OxObjObject>(),
            AllocType::String => exit(-1),
        };

        if !self.allocator.is_space_available(&self.memory, amount) {
            //self.compactify_memory();

            if !self.allocator.is_space_available(&self.memory, amount) {
                self.expand_memory();
            }
        }
        self.allocator.allocated_count += 1;

        let layout = self.allocator.allocate(amount);

        unsafe {
            match &kind {
                AllocType::Int => {
                    let mut target = &mut self.memory[layout.range()];
                    let (before, body, after) = target.align_to_mut::<OxObjInt>();

                    if body.len() == 0 {
                        println!("error aligning {} bytes. Before {}, After {}", amount, before.len(), after.len());
                        exit(-1)
                    }

                    let allocation = &mut body[0] as *mut OxObjInt;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
                        padding_after: layout.padding,
                        mem_type: kind,
                    };
                    (*allocation).data = 0;

                    return ObjRef::Int(&mut (*allocation).header, &mut (*allocation).data);
                },
                AllocType::Float => {
                    let mut target = &mut self.memory[layout.range()];
                    let (_, body, _) = target.align_to_mut::<OxObjFloat>();

                    let allocation = &mut body[0] as *mut OxObjFloat;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
                        padding_after: layout.padding,
                        mem_type: kind
                    };
                    (*allocation).data = 0f64;

                    return ObjRef::Float(&mut (*allocation).header, &mut (*allocation).data);
                },
                AllocType::Object => {
                    let mut target = &mut self.memory[layout.range()];
                    let (_, body, _) = target.align_to_mut::<OxObjObject>();

                    let allocation = &mut body[0] as *mut OxObjObject;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
                        padding_after: layout.padding,
                        mem_type: kind
                    };
                    (*allocation).data = Object {
                        fields: HashMap::new()
                    };

                    return ObjRef::Object(&mut (*allocation).header, &mut (*allocation).data);
                },
                AllocType::String => exit(-1),
            }
        };

    }


    fn compactify_memory(&mut self) {
        //TODO: update references to moved objects
        //TODO: ensure alignment
        const HEADER_SIZE: usize = size_of::<AllocHeader>();
        unsafe {

            let mut next_ptr = 0;
            let mut visited = 0;
            while self.allocator.allocated_count < visited {
                let (_, header_raw, _) = self.memory[..HEADER_SIZE].align_to::<AllocHeader>();
                let header = &header_raw[0];

                if header.reference_count > 0 {
                    next_ptr = header.size as usize + header.padding_after as usize;
                    visited += 1;
                } else {
                    self.memory.drain(next_ptr .. next_ptr + header.size as usize + header.padding_after as usize);
                    self.allocator.allocated_count -= 1;
                }

            }
        }
    }

    fn expand_memory(&mut self) {
        let current_len = self.memory.len();
        self.memory.resize_with(current_len * 2, || 0);
        vm_debug!("memory size doubled to {}", self.memory.len());
    }
}

impl OxAllocator {

    fn is_space_available(&self, memory: &Vec<u8>, amount: usize) -> bool {
        self.top + amount < memory.len()
    }

    fn allocate(&mut self, amount: usize) -> AllocLayout {
        let ptr = self.top;
        self.top = self.top + amount;
        let aligned_address = (self.top + 8 - 1) & (!(8 - 1 ));

        let padding = (aligned_address - self.top) as u8;
        self.top = aligned_address;

        AllocLayout {
            ptr,
            amount,
            padding
        }
    }
}

impl AllocLayout {
    fn range(&self) -> std::ops::Range<usize> {
        self.ptr .. self.ptr + self.amount + self.padding as usize
    }
}



