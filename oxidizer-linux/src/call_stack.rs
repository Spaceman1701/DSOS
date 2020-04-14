use crate::object::ObjRef;

pub struct CallStack<'heap> {
    stack: Vec<StackFrame<'heap>>,
    exe_stack: Vec<ExecutionStack<'heap>>,
    ip_stack: Vec<usize>
}

pub struct StackFrame<'heap> {
    locals: Vec<ObjRef<'heap>>
}

pub struct ExecutionStack<'heap> {
    stack: Vec<ObjRef<'heap>>
}

impl <'heap> CallStack <'heap> {
    pub fn new() -> CallStack<'heap> {
        CallStack {
            stack: vec![StackFrame::new()],
            exe_stack: vec![ExecutionStack::new()],
            ip_stack: vec![0]
        }
    }

    pub fn push_new(&mut self, new_ip: usize) {
        self.stack.push(StackFrame::new());
        self.exe_stack.push(ExecutionStack::new());
        self.ip_stack.push(new_ip);
    }

    pub fn pop(&mut self) -> bool {
        self.stack.pop();
        self.exe_stack.pop();
        self.ip_stack.pop();
        return self.stack.len() != 0;
    }

    #[inline]
    pub fn ip(&mut self) -> &mut usize {
        self.ip_stack.last_mut().unwrap()
    }

    pub fn active_frame(&mut self) -> & mut StackFrame<'heap> {
        match self.stack.last_mut() {
            None => unreachable!(),
            Some(value) => value,
        }
    }

    pub fn active_exe(&mut self) -> &mut ExecutionStack<'heap> {
        match self.exe_stack.last_mut() {
            None => unreachable!(),
            Some(value) => value
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

    pub fn print_debug_info(&self) {
        println!("---- STACK FRAME ----");
        for (index, value) in self.locals.iter().enumerate() {
            println!("{} -- {:?}", index, value);
        }
    }
}

impl <'heap> ExecutionStack<'heap> {
    pub fn new() -> ExecutionStack<'heap> {
        ExecutionStack {
            stack: vec![]
        }
    }

    pub fn pop_optional(&mut self) -> Option<ObjRef<'heap>> {
        self.stack.pop()
    }

    pub fn pop(&mut self) -> ObjRef<'heap> {
        self.pop_optional().unwrap()
    }

    pub fn push(&mut self, obj: ObjRef<'heap>) {
        self.stack.push(obj)
    }

    pub fn peek(&mut self) -> &mut ObjRef<'heap> {
        self.stack.last_mut().unwrap()
    }

    pub fn swap_from_end(&mut self, a: usize, b: usize) {
        let a_from_end = self.stack.len() - 1 - a;
        let b_from_end = self.stack.len() - 1 - b;
        self.stack.swap(a_from_end, b_from_end);
    }
}
