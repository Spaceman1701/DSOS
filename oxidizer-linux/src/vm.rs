use crate::program;
use std::process::exit;
use crate::instruction::Instruction;
use crate::memory;
use crate::memory::{Heap, AllocType};
use crate::object::{ObjRef};
use crate::call_stack::{CallStack};
#[macro_use] use crate::debug_macros;


macro_rules! numeric_binop {
    ($operator: tt, $vm: expr) => {
        {
        let second = $vm.call_stack.active_exe().pop();
        let first = $vm.call_stack.active_exe().pop();
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Float(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_float(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator ((**right) as f64);
                let result = $vm.allocate_and_assign_float(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Int(_, left), ObjRef::Float(_, right)) => {
                let value = ((**left) as f64) $operator **right;
                let result = $vm.allocate_and_assign_float(value);
                $vm.call_stack.active_exe().push(result);
            }

            _ => exit(-1)
        }
        }
    }
}

macro_rules! comparison {
    ($operator: tt, $vm: expr) => {
    {
        vm_debug!("comparison");
        let second = $vm.call_stack.active_exe().pop();
        let first = $vm.call_stack.active_exe().pop();
        vm_debug!("loaded two refs off the stack");
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                vm_debug!("{} comp {}", left, right);
                let value = if **left $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Float(_, right)) => {
                let value = if **left $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Int(_, right)) => {
                let value = if **left $operator ((**right) as f64) {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }

            (ObjRef::Int(_, left), ObjRef::Float(_, right)) => {
                let value = if ((**left) as f64) $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }

            _ => exit(-1)
        }
    }
    }
}

macro_rules! int_only_binop {
    ($operator: tt, $vm: expr) => {
        {
        let second = $vm.call_stack.active_exe().pop();
        let first = $vm.call_stack.active_exe().pop();
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_int(value);
                $vm.call_stack.active_exe().push(result);
            }
            _ => exit(-1)
        }
        }
    }
}



pub struct VM<'a> {
    program: &'a program::Program,
    heap: Heap<'a>,
    call_stack: CallStack<'a>
}

impl <'program> VM<'program> {
    pub fn new(program: &'program program::Program) -> VM<'program> {
        VM {
            program,
            heap: memory::Heap::new(),
            call_stack: CallStack::new()
        }
    }

    #[inline]
    fn ip(&mut self) -> &mut usize {
        self.call_stack.ip()
    }

    pub fn execute(&mut self) {

        let main_ip = match self.program.lookup_function("main") {
            None => {
                panic!("failed to find function 'main'");
            },
            Some(ptr) => *ptr,
        };

        (*self.ip()) = main_ip;

        vm_debug!("main function is at {}", self.ip());

        while !self.program.is_done(*self.ip()) {
            match self.program.get_ins(*self.ip()) {
                None => {
                    panic!("instruction decode error at {}", self.ip());
                },
                Some((ins, skip)) => {
                    self.perform_action(ins, skip);
                },
            }
        }
    }

    fn perform_action(&mut self, ins: Instruction, skip: usize) {
        let mut control_change = false;
        match ins {
            Instruction::Store(ptr) => {
                vm_debug!("Store {}", ptr);
                let obj = self.call_stack.active_exe().pop();
                let frame = self.call_stack.active_frame();
                frame.store(&obj, ptr);
            },
            Instruction::LoadConstInt(value) => {
                vm_debug!("LoadConstInt({})", value);
                let obj = self.allocate_and_assign_int(value);
                self.call_stack.active_exe().push(obj);
            },
            Instruction::LoadConstFloat(value) => {
                let obj = self.allocate_and_assign_float(value);
                self.call_stack.active_exe().push(obj);
            },
            Instruction::LoadConstStr(ptr) => {
                let the_str = self.read_string_or_exit(ptr);
                let obj = ObjRef::String(None, the_str);
                self.call_stack.active_exe().push(obj);
            },
            Instruction::LoadVar(ptr) => {
                vm_debug!("LoadVar({})", ptr);
                let obj = self.call_stack.active_frame().get(ptr).unwrap();
                self.call_stack.active_exe().push(obj);
            },
            Instruction::CreateObject => {},
            Instruction::SliceList => {},
            Instruction::Add => {numeric_binop!(+, self)},
            Instruction::Sub => {numeric_binop!(-, self)},
            Instruction::Mul => {numeric_binop!(*, self)},
            Instruction::Div => {numeric_binop!(/, self)},
            Instruction::Mod => {numeric_binop!(%, self)},
            Instruction::Concat => self.do_concat(),
            Instruction::Pow => {},
            Instruction::LAnd => {int_only_binop!(&, self)},
            Instruction::LOr => {int_only_binop!(|, self)},
            Instruction::CompG => {comparison!(>, self)},
            Instruction::CompL => {comparison!(<, self)},
            Instruction::CompEq => {comparison!(==, self)},
            Instruction::LoadMember => {},
            Instruction::StoreMember => {},
            Instruction::Call => {
                vm_debug!("debug CALL");
                let function_name = self.call_stack.active_exe().pop();
                let param_count = self.call_stack.active_exe().pop();

                match (&function_name, &param_count) {
                    (ObjRef::String(_, fun_name), ObjRef::Int(_, count_num)) => {
                        let mut params: Vec<ObjRef> = Vec::new();
                        for _ in 0..**count_num {
                            params.push(self.call_stack.active_exe().pop());
                        }
                        if *fun_name == "println" {
                            match params[0] {
                                ObjRef::String(_, ref to_print) => {println!("{}", to_print)},
                                ObjRef::Int(_, ref to_print) => {println!("{}", to_print)},
                                ObjRef::Float(_, ref to_print) => {println!("{}", to_print)},
                                ObjRef::Object(_, ref obj) => {println!("Object with {} fields", obj.fields.len())}
                            }
                        } else if *fun_name == "exit" {
                            exit(0);
                        } else {
                            match self.program.lookup_function(*fun_name) {
                                None => panic!("failed to find function '{}'", *fun_name),
                                Some(ptr) => {
                                    control_change = true;
                                    (*self.ip()) = *self.ip() + skip;
                                    self.call_stack.push_new(*ptr);
                                    for (i, p) in params.iter().enumerate() {
                                        self.call_stack.active_frame().store(p, i as u16);
                                    }
                                }
                            }
                        }
                    }
                    _ => panic!("bad function call")
                }

            },
            Instruction::Jump(ptr) => {
                vm_debug!("Jump {}", ptr);
                (*self.ip()) = ptr as usize;
                control_change = true;
            },
            Instruction::IfFalse(ptr) => {
                vm_debug!("IfFalse at ip {}", *self.ip());
                vm_debug!("IfFalse {}", ptr);
                let cond = self.call_stack.active_exe().pop();
                match cond {
                    ObjRef::Int(_, 0) => {
                        (*self.ip()) = ptr as usize;
                        control_change = true;
                    },
                    _ => ()
                }
            },
            Instruction::Ret => {
                control_change = true;
                match self.call_stack.active_exe().pop_optional() {
                    None => {
                        if !self.call_stack.pop() {
                            exit(0)
                        }
                    }
                    Some(value) => {
                        if !self.call_stack.pop() {
                            exit(0);
                        }
                        self.call_stack.active_exe().push(value);
                    }
                }

            },
            Instruction::Throw => {},
            Instruction::PostEvent => {},
            Instruction::WaitEvent => {},
            Instruction::Spawn => {},
            Instruction::WriteChannel => {},
            Instruction::ReadChannel => {},
            Instruction::Not => {},
            Instruction::XOr => {int_only_binop!(^, self)},
            Instruction::BAnd => {int_only_binop!(&, self)},
            Instruction::BOr => {int_only_binop!(|, self)},
            Instruction::LeftShift => {int_only_binop!(<<, self)},
            Instruction::RightShift => {int_only_binop!(>>, self)},
            Instruction::URightShift => {int_only_binop!(<<, self)},
            Instruction::Modulo => {panic!("unsupported operation")},
            Instruction::BCompliment => {},
            Instruction::Dup => {
                let tos = self.call_stack.active_exe().peek().clone();
                self.call_stack.active_exe().push(tos);
            },
            Instruction::Consume => {
                self.call_stack.active_exe().pop_optional();
            },
        }

        if !control_change {
            (*self.ip()) += skip;
        }
    }

    #[inline]
    fn do_concat(&mut self) {
        vm_debug!("Concat");
        let first = self.call_stack.active_exe().pop();
        let second = self.call_stack.active_exe().pop();

        match (&second, &first) {
            (ObjRef::String(_, left), ObjRef::String(_, right)) => {
                let concat = format!("{}{}", left, right);
                let result = self.heap.allocate_str(concat.as_str());
                self.call_stack.active_exe().push(result);
            }
            (ObjRef::Int(_, left), ObjRef::String(_, right)) => {
                let concat = format!("{}{}", left, right);
                let result = self.heap.allocate_str(concat.as_str());
                self.call_stack.active_exe().push(result);
            }
            (ObjRef::Float(_, left), ObjRef::String(_, right)) => {
                let concat = format!("{}{}", left, right);
                let result = self.heap.allocate_str(concat.as_str());
                self.call_stack.active_exe().push(result);
            }
            (ObjRef::String(_, left), ObjRef::Int(_, right)) => {
                let concat = format!("{}{}", left, right);
                let result = self.heap.allocate_str(concat.as_str());
                self.call_stack.active_exe().push(result);
            }
            (ObjRef::String(_, left), ObjRef::Float(_, right)) => {
                let concat = format!("{}{}", left, right);
                let result = self.heap.allocate_str(concat.as_str());
                self.call_stack.active_exe().push(result);
            }

            _ => panic!("alkdjaklsdjakdsl")
        }
    }

    fn read_string_or_exit(&self, ptr: u32) -> &'program str {
        return match self.program.read_str(ptr) {
            Ok(the_str) => the_str,
            Err(_) => {
                panic!("error reading string at {}", ptr);
            },
        }
    }

    fn allocate_and_assign_int(&mut self, value: i64) -> ObjRef<'program> {
        let mut obj = self.heap.allocate(AllocType::Int);
        match obj {
            ObjRef::Int(_, ref mut i) => {
                **i = value;
            }
            _ => unreachable!()
        };
        obj
    }

    fn allocate_and_assign_float(&mut self, value: f64) -> ObjRef<'program> {
        let mut obj = self.heap.allocate(AllocType::Float);
        match obj {
            ObjRef::Float(_, ref mut i) => {
                **i = value;
            }
            _ => unreachable!()
        };
        obj
    }
}



