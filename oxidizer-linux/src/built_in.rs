use crate::object::{Object, ObjRef};
use std::process::exit;

pub fn new_http_event_template<'heap>(return_value: &mut ObjRef<'heap>, params: Vec<ObjRef<'heap>>) {
    let mut params_mut = params;
    match (return_value, &params_mut[..]) {
        (ObjRef::Object(_, ref mut ret_object), [method@ObjRef::String(_, _), path@ObjRef::String(_,_)]) => {
            ret_object.fields.insert("method", params_mut.remove(0));
            ret_object.fields.insert("path", params_mut.remove(0));
        }
        _ => {
            println!("error calling newHttpEventTemplate built-in function");
            exit(-1);
        }
    }
}

