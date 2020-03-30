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
    mem_type: AllocType
}

pub struct OxAllocator {
    top: usize,
    free: usize,
}

pub struct Heap<'heap> {
    memory: Vec<u8>,
    allocator: OxAllocator,
    phantom: PhantomData<&'heap u8> //
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
            },
            phantom: PhantomData
        }
    }

    pub fn allocate_str(&mut self, value: &str) -> ObjRef<'heap> {
        let amount = value.len() + size_of::<AllocHeader>();
        if !self.allocator.is_space_available(&self.memory, amount) {
            self.compactify_memory();

            if !self.allocator.is_space_available(&self.memory, amount) {
                self.expand_memory();
            }
        }

        let ptr = self.allocator.allocate(amount);

        unsafe {
            let header = self.leak_header(ptr);
            (*header).size = amount as u32;
            (*header).mem_type = AllocType::String;
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
            self.compactify_memory();

            if !self.allocator.is_space_available(&self.memory, amount) {
                self.expand_memory();
            }
        }

        let ptr = self.allocator.allocate(amount);

        unsafe {
            match &kind {
                AllocType::Int => {
                    let mut target = &mut self.memory[ptr..ptr + amount];
                    let (before, body, after) = target.align_to_mut::<OxObjInt>();

                    let allocation = &mut body[0] as *mut OxObjInt;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
                        mem_type: kind
                    };
                    (*allocation).data = 0;

                    return ObjRef::Int(&mut (*allocation).header, &mut (*allocation).data);
                },
                AllocType::Float => {
                    let mut target = &mut self.memory[ptr..ptr + amount];
                    let (_, body, _) = target.align_to_mut::<OxObjFloat>();

                    let allocation = &mut body[0] as *mut OxObjFloat;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
                        mem_type: kind
                    };
                    (*allocation).data = 0f64;

                    return ObjRef::Float(&mut (*allocation).header, &mut (*allocation).data);
                },
                AllocType::Object => {
                    let mut target = &mut self.memory[ptr..ptr + amount];
                    let (_, body, _) = target.align_to_mut::<OxObjObject>();

                    let allocation = &mut body[0] as *mut OxObjObject;

                    (*allocation).header = AllocHeader {
                        reference_count: 1,
                        size: amount as u32,
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
    }

    fn expand_memory(&mut self) {
        let current_len = self.memory.len();
        self.memory.resize_with(current_len * 2, || 0);
    }
}

impl OxAllocator {

    fn is_space_available(&self, memory: &Vec<u8>, amount: usize) -> bool {
        self.top + amount >= memory.len()
    }

    fn allocate(&mut self, amount: usize) -> usize {
        let ptr = self.top;
        self.top = self.top + amount;
        ptr
    }
}



