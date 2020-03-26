use crate::program;
use std::process::exit;
use crate::instruction::Instruction;
use crate::memory;
use crate::memory::{Heap, AllocType};
use crate::object::{ObjRef};

pub struct VM<'a> {
    program: &'a program::Program,
    ip: usize,
    exe_stack: ExecutionStack<'a>,
    heap: Heap<'a>
}

struct ExecutionStack<'a> {
    stack: Vec<ObjRef<'a>>
}



impl <'program> VM<'program> {
    pub fn new(program: &'program program::Program) -> VM<'program> {
        VM {
            program,
            ip: 0 as usize,
            exe_stack: ExecutionStack {
                stack: Vec::new()
            },
            heap: memory::Heap::new()
        }
    }

    pub fn execute(&'program mut self) {
        let mut ip = self.ip;
        let mut i = 0;
        while self.program.is_done(ip) {
            match self.program.get_ins(ip) {
                None => {
                    eprintln!("instruction decode error at {}", ip);
                },
                Some((ins, skip)) => {
                    self.perform_action(ins, skip);
                },
            }
            ip = self.ip;
//
            i += 1;
            if i > 3 {
                break;
            }
        }
    }

    fn perform_action(&mut self, ins: Instruction, skip: usize) {
        let mut control_change = false;
        match ins {
            Instruction::Store(ptr) => {},
            Instruction::LoadConstInt(value) => {
                let obj = self.allocate_and_assign_int(value);

                match &obj {
                    ObjRef::Int(i) => println!("{}", i),
                    _ => unreachable!()
                }

                self.exe_stack.stack.push(obj);
            },
            Instruction::LoadConstFloat(value) => {},
            Instruction::LoadConstStr(ptr) => {
                let the_str = self.read_string_or_exit(ptr);
                let obj = ObjRef::String(the_str);
                self.exe_stack.stack.push(obj);
            },
            Instruction::LoadVar(ptr) => {},
            Instruction::CreateObject => {},
            Instruction::SliceList => {},
            Instruction::Add => self.do_add(),
            Instruction::Sub => self.do_sub(),
            Instruction::Mul => self.do_mul(),
            Instruction::Div => self.do_div(),
            Instruction::Mod => self.do_mod(),
            Instruction::Concat => self.do_concat(),
            Instruction::Pow => {},
            Instruction::LAnd => {},
            Instruction::LOr => {},
            Instruction::CompG => {},
            Instruction::CompL => {},
            Instruction::CompEq => {},
            Instruction::LoadMember => {},
            Instruction::StoreMember => {},
            Instruction::Call => {
                println!("Call");
                let function_name = self.exe_stack.stack.pop();
                let param_count = self.exe_stack.stack.pop();

                function_name.and_then(|name| param_count.map(|count| (name, count)) )
                    .map(|(name, count)| {
                        match (name, count) {
                            (ObjRef::String(fun_name), ObjRef::Int(count_num)) => {
                                let mut params: Vec<ObjRef> = Vec::new();
                                for _ in 0..*count_num {
                                    params.push(self.exe_stack.stack.pop().unwrap());
                                }
                                if fun_name == "println" {
                                    match params[0] {
                                        ObjRef::String(ref to_print) => {println!("{}", to_print)},
                                        ObjRef::Int(ref to_print) => {println!("{}", to_print)},
                                        ObjRef::Float(ref to_print) => {println!("{}", to_print)},
                                        ObjRef::Object(ref obj) => {println!("Object with {} fields", obj.fields.len())}
                                    }
                                }
                            }
                            _ => exit(-1)
                        }

                    });

            },
            Instruction::Jump(ptr) => {
                println!("Jump {}", ptr);
                self.ip = ptr as usize;
                control_change = true;
            },
            Instruction::IfFalse(ptr) => {
                println!("IfFalse {}", ptr);
                let cond = self.exe_stack.stack.pop();
                match cond {
                    Some(ObjRef::Int(0)) => {
                        self.ip = ptr as usize;
                        control_change = true;
                    },
                    _ => ()
                }
            },
            Instruction::Ret => {},
            Instruction::Throw => {},
            Instruction::PostEvent => {},
            Instruction::WaitEvent => {},
            Instruction::Spawn => {},
            Instruction::WriteChannel => {},
            Instruction::ReadChannel => {},
            Instruction::Not => {},
            Instruction::XOr => {},
            Instruction::BAnd => {},
            Instruction::BOr => {},
            Instruction::LeftShift => {},
            Instruction::RightShift => {},
            Instruction::URightShift => {},
            Instruction::Modulo => {},
            Instruction::BCompliment => {},
            Instruction::Dup => {},
            Instruction::Consume => {},
        }

        if !control_change {
            self.ip += skip;
        }
    }

    #[inline]
    fn do_add(&mut self) {
        println!("add");
        let first = self.exe_stack.stack.pop().unwrap();
        let second = self.exe_stack.stack.pop().unwrap();
        match (first, second) {
            (ObjRef::Int(left), ObjRef::Int(right)) => {
                let value = *left + *right;
                println!("ADD RESULT: {}", value);
                let result = self.allocate_and_assign_int(value);
                self.exe_stack.stack.push(result);
            }

            (ObjRef::Float(left), ObjRef::Float(right)) => {
                let value = *left + *right;
                let result = self.allocate_and_assign_float(value);
                self.exe_stack.stack.push(result);
            }

            (ObjRef::Float(left), ObjRef::Int(right)) => {
                let value = *left + ((*right) as f64);
                let result = self.allocate_and_assign_float(value);
                self.exe_stack.stack.push(result);
            }

            (ObjRef::Int(left), ObjRef::Float(right)) => {
                let value = ((*left) as f64) + *right;
                let result = self.allocate_and_assign_float(value);
                self.exe_stack.stack.push(result);
            }

            _ => exit(-1)

        }
    }

    #[inline]
    fn do_sub(&mut self) {
        println!("sub");
//        let first = self.exe_stack.stack.pop().unwrap();
//        let second = self.exe_stack.stack.pop().unwrap();
//        match (first, second) {
//            (ObjRef::Int(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Int(left - right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left - right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Float(left - right as f64);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Int(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left as f64 - right);
//                self.exe_stack.stack.push(result);
//            }
//
//            _ => exit(-1)
//
//        }
    }

    #[inline]
    fn do_mul(&mut self) {
        println!("mul");
//        let first = self.exe_stack.stack.pop().unwrap();
//        let second = self.exe_stack.stack.pop().unwrap();
//        match (first, second) {
//            (ObjRef::Int(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Int(left * right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left * right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Float(left * right as f64);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Int(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left as f64 * right);
//                self.exe_stack.stack.push(result);
//            }
//
//            _ => exit(-1)
//
//        }
    }

    #[inline]
    fn do_div(&mut self) {
        println!("div");
//        let first = self.exe_stack.stack.pop().unwrap();
//        let second = self.exe_stack.stack.pop().unwrap();
//        match (first, second) {
//            (ObjRef::Int(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Int(left / right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left / right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Float(left / right as f64);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Int(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left as f64 / right);
//                self.exe_stack.stack.push(result);
//            }
//
//            _ => exit(-1)
//
//        }
    }

    #[inline]
    fn do_mod(&mut self) {
        println!("mod");
//        let first = self.exe_stack.stack.pop().unwrap();
//        let second = self.exe_stack.stack.pop().unwrap();
//        match (first, second) {
//            (ObjRef::Int(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Int(left % right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left % right);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Float(left), ObjRef::Int(right)) => {
//                let result = ObjRef::Float(left % right as f64);
//                self.exe_stack.stack.push(result);
//            }
//
//            (ObjRef::Int(left), ObjRef::Float(right)) => {
//                let result = ObjRef::Float(left as f64 % right);
//                self.exe_stack.stack.push(result);
//            }
//
//            _ => exit(-1)
//
//        }
    }

    #[inline]
    fn do_concat(&mut self) {
        println!("Concat");
//        let first = self.exe_stack.stack.pop().unwrap();
//        let second = self.exe_stack.stack.pop().unwrap();
//        let concat_str = match (first, second) {
//            (ObjRef::Str(left), ObjRef::Str(right)) => left + right,
//            (ObjRef::Str(left), ObjRef::Int(right)) => left + right.to_string(),
//            _ => exit(-1)
//        };
    }

    fn read_string_or_exit(&self, ptr: u32) -> &'program str {
        return match self.program.read_str(ptr) {
            Ok(the_str) => the_str,
            Err(_) => {
                eprintln!("error reading string at {}", ptr);
                exit(-1)
            },
        }
    }

    fn allocate_and_assign_int(&mut self, value: i64) -> ObjRef<'program> {
        let mut obj = self.heap.allocate(AllocType::Int);
        match obj {
            ObjRef::Int(ref mut i) => {
                **i = value;
            }
            _ => unreachable!()
        };
        obj
    }

    fn allocate_and_assign_float(&mut self, value: f64) -> ObjRef<'program> {
        let mut obj = self.heap.allocate(AllocType::Float);
        match obj {
            ObjRef::Float(ref mut i) => {
                **i = value;
            }
            _ => unreachable!()
        };
        obj
    }
}