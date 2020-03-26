use std::collections::HashMap;

pub struct Object<'heap> {
    pub fields: HashMap<&'heap str, ObjRef<'heap>> //TODO: reimplement HashMap and Vec
}

pub enum ObjRef<'heap> {
    Int(&'heap mut i64),
    Float(&'heap mut f64),
    String(&'heap str),
    Object(&'heap mut Object<'heap>)
}