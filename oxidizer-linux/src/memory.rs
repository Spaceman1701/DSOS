use std::mem;
use std::marker::Sized;
use std::collections::HashMap;

pub struct OxObj<'pointers> {
    reference_count: u32,
    data: OxObjData<'pointers>
}

pub enum OxObjData<'pointers> {
    Int(i64),
    Float(f64),
    String(&'pointers str),
    Object(Object<'pointers>)
}

pub struct Object<'pointer> {
    fields: HashMap<&'pointer str, ObjRef<'pointer>>
}

pub struct ObjRef<'pointers> {
    target: &'pointers OxObj<'pointers>
}



