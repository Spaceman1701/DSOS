use crate::object::ObjRef;


pub struct CallStack<'heap> {
    stack: Vec<StackFrame<'heap>>,
}

pub struct StackFrame<'heap> {
    locals: Vec<ObjRef<'heap>>
}

impl <'heap> CallStack <'heap> {
    pub fn new() -> CallStack<'heap> {
        CallStack {
            stack: vec![StackFrame::new()]
        }
    }

    pub fn push_new(&mut self) {
        self.stack.push(StackFrame::new())
    }

    pub fn peek(&mut self) -> & mut StackFrame<'heap> {
        match self.stack.first_mut() {
            None => unreachable!(),
            Some(value) => value,
        }
    }
}

impl <'heap> StackFrame<'heap> {
    pub fn new() -> StackFrame<'heap> {

        let initial = Vec::with_capacity(64);

        StackFrame {
            locals: initial
        }
    }

    pub fn store(&mut self, object: &ObjRef<'heap>, index: u16) {
        if self.locals.len() > index as usize {
            std::mem::replace(&mut self.locals[index as usize], object.clone());
        } else {
            self.locals.push(object.clone());
        }
    }

    pub fn get(&self, index: u16) -> Option<ObjRef<'heap>> {
        match self.locals.get(index as usize) {
            None => None,
            Some(obj) => {
                let copy = obj.clone();
                Some(copy)
            }
        }
    }
}

