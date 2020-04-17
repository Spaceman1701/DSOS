
use crate::call_stack::{CallStack};

pub struct Coroutine<'heap> {
    stack: CallStack<'heap>,
    thread_id: usize,
    parked: bool
}

impl <'heap> Coroutine <'heap> {
    pub fn new(thread_id: usize) -> Coroutine<'heap> {
        Coroutine {
            stack: CallStack::new(),
            thread_id,
            parked: false,
        }
    }

    pub fn stack_mut(&mut self) -> &mut CallStack<'heap> {
        &mut self.stack
    }

    pub fn stack(&self) -> &CallStack<'heap> {
        &self.stack
    }

    pub fn get_thread_id(&self) -> usize {
        self.thread_id
    }

    pub fn park(&mut self) {
        self.parked = true;
    }

    pub fn unpark(&mut self) {
        self.parked = false;
    }

    pub fn is_parked(&self) -> bool {
        self.parked
    }
}