use std::collections::HashMap;

use crate::memory;
use crate::memory::AllocHeader;

pub struct Object<'heap> {
    pub fields: HashMap<&'heap str, ObjRef<'heap>> //TODO: reimplement HashMap and Vec
}

pub enum ObjRef<'heap> {
    Int(&'heap mut memory::AllocHeader, &'heap mut i64),
    Float(&'heap mut memory::AllocHeader, &'heap mut f64),
    String(Option<&'heap mut memory::AllocHeader>, &'heap str),
    Object(&'heap mut memory::AllocHeader, &'heap mut Object<'heap>)
}

impl Clone for ObjRef <'_> {
    fn clone(&self) -> Self {
        unsafe {
            match self {
                ObjRef::Int(h, v) => {
                    let header_ptr: &AllocHeader = *(h as *const &mut AllocHeader);
                    let header_ptr_const = header_ptr as *const AllocHeader;
                    let header_ptr_mut = header_ptr_const as *mut AllocHeader;
                    let header_out = &mut *header_ptr_mut;

                    let value_ptr: &i64 = *(v as * const &mut i64);
                    let value_ptr_const = value_ptr as *const i64;
                    let value_ptr_mut = value_ptr_const as *mut i64;
                    let value_out = &mut *value_ptr_mut;

                    ObjRef::Int(header_out, value_out)
                },
                ObjRef::Float(h, v) => {
                    let header_ptr: &AllocHeader = *(h as *const &mut AllocHeader);
                    let header_ptr_const = header_ptr as *const AllocHeader;
                    let header_ptr_mut = header_ptr_const as *mut AllocHeader;
                    let header_out = &mut *header_ptr_mut;

                    let value_ptr: &f64 = *(v as * const &mut f64);
                    let value_ptr_const = value_ptr as *const f64;
                    let value_ptr_mut = value_ptr_const as *mut f64;
                    let value_out = &mut *value_ptr_mut;

                    ObjRef::Float(header_out, value_out)
                },
                ObjRef::Object(h, v) => {
                    let header_ptr: &AllocHeader = *(h as *const &mut AllocHeader);
                    let header_ptr_const = header_ptr as *const AllocHeader;
                    let header_ptr_mut = header_ptr_const as *mut AllocHeader;
                    let header_out = &mut *header_ptr_mut;

                    let value_ptr: &Object = *(v as * const &mut Object);
                    let value_ptr_const = value_ptr as *const Object;
                    let value_ptr_mut = value_ptr_const as *mut Object;
                    let value_out = &mut *value_ptr_mut;

                    ObjRef::Object(header_out, value_out)
                },
                ObjRef::String(ref header_option, v) => {
                    let header_out = header_option.as_ref().map(|h| {
                        let header_ptr: &AllocHeader = *(h as *const &mut AllocHeader);
                        let header_ptr_const = header_ptr as *const AllocHeader;
                        let header_ptr_mut = header_ptr_const as *mut AllocHeader;
                        &mut *header_ptr_mut
                    });

                    let value_out = &*(*(v as *const &str) as *const str); //yikes

                    ObjRef::String(header_out, value_out)
                }
            }
        }

    }
}

unsafe impl Drop for ObjRef<'_> {
    fn drop(&mut self) {
//        let mut header_option= match self {
//            ObjRef::Int(h, _) => &mut Some(*h),
//            ObjRef::Float(h, _) => &mut Some(*h),
//            ObjRef::String(h, _) => h,
//            ObjRef::Object(h, _) => &mut Some(*h),
//        };
//
//        header_option.map(|mut header| {
//            header.reference_count -= 1;
//            println!("dropped reference");
//        });

//        match self {
//            ObjRef::Int(h, _) => {h.reference_count -= 1},
//            ObjRef::Float(h, _) => {h.reference_count -= 1},
//            ObjRef::String(opt, _) => {
//                match &opt {
//                    None => {},
//                    Some(ref mut h) => {h.reference_count -= 1},
//                }
//            },
//            ObjRef::Object(h, _) => {h.reference_count -= 1},
//    }
    }
}