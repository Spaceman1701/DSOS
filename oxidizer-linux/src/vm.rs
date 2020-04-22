use crate::program;
use std::process::exit;
use crate::instruction::Instruction;
use crate::memory;
use crate::memory::{Heap, AllocType};
use crate::object::{ObjRef};
use crate::call_stack::{CallStack};
#[macro_use] use crate::debug_macros;
use crate::program::FieldData;
use crate::coroutine::Coroutine;
use crate::event_manager::{EventManager, Event};
use std::sync::mpsc::Sender;
use std::collections::HashMap;
use std::io::Write;
use crate::http::HttpResponse;

macro_rules! numeric_binop {
    ($operator: tt, $vm: expr) => {
        {
        let second = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        let first = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Float(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_float(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator ((**right) as f64);
                let result = $vm.allocate_and_assign_float(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Int(_, left), ObjRef::Float(_, right)) => {
                let value = ((**left) as f64) $operator **right;
                let result = $vm.allocate_and_assign_float(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
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
        let second = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        let first = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        vm_debug!("loaded two refs off the stack");
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                vm_debug!("{} comp {}", left, right);
                let value = if **left $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Float(_, right)) => {
                let value = if **left $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Float(_, left), ObjRef::Int(_, right)) => {
                let value = if **left $operator ((**right) as f64) {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Int(_, left), ObjRef::Float(_, right)) => {
                let value = if ((**left) as f64) $operator **right {1} else {0};
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (ObjRef::Object(_, _), _) => {
                let result = $vm.allocate_and_assign_int(0);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            (_, ObjRef::Object(_, _)) => {
                let result = $vm.allocate_and_assign_int(0);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }

            _ => {
                println!("can't do comparison with {:?} and {:?}", first, second);
                $vm.exit(-1);
            }
        }
    }
    }
}

macro_rules! int_only_binop {
    ($operator: tt, $vm: expr) => {
        {
        let second = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        let first = $vm.cur_thread_mut().stack_mut().active_exe().pop();
        match (&first, &second) {
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => {
                let value = **left $operator **right;
                let result = $vm.allocate_and_assign_int(value);
                $vm.cur_thread_mut().stack_mut().active_exe().push(result);
            }
            _ => {
                println!("can't do int only binop with {:?} and {:?}", first, second);
                $vm.exit(-1);
            }
        }
        }
    }
}

macro_rules! concat_branch {
    ($vm: expr, $left: expr, $right: expr) => {{
        let concat = format!("{}{}", $left, $right);
        let result = $vm.heap.allocate_str(concat.as_str());
        $vm.cur_thread_mut().stack_mut().active_exe().push(result);
    }}
}


pub struct VM<'a> {
    program: &'a program::Program,
    heap: Heap<'a>,
    threads: Vec<Coroutine<'a>>,
    active_thread_id: usize,
    active_thread_index: usize,
    event_manager: EventManager,
    event_buffer: Vec<Event>,

    events_in_progress: HashMap<i64, Event>
}

impl <'program> VM<'program> {
    pub fn new(program: &'program program::Program) -> VM<'program> {
        VM {
            program,
            heap: memory::Heap::new(),
            threads: vec![Coroutine::new(0)],
            active_thread_index: 0,
            active_thread_id: 0,
            event_manager: EventManager::new(),
            event_buffer: vec![],
            events_in_progress: HashMap::new(),
        }
    }

    #[inline]
    fn cur_thread_mut(&mut self) -> &mut Coroutine<'program> {
        self.threads.get_mut(self.active_thread_index).unwrap()
    }

    #[inline]
    fn cur_thread(&self) -> &Coroutine<'program> {
        self.threads.get(self.active_thread_index).unwrap()
    }

    fn do_pop(&mut self) -> ObjRef<'program> {
        match self.cur_thread_mut().stack_mut().active_exe().pop_optional() {
            Some(res) => res,
            None => {
                println!("nothing on exe stack to pop");
                self.exit(-1)
            }
        }
    }

    fn do_push(&mut self, obj: ObjRef<'program>) {
        self.cur_thread_mut().stack_mut().active_exe().push(obj);
    }

    #[inline]
    fn ip(&mut self) -> &mut usize {
        self.cur_thread_mut().stack_mut().ip()
    }

    fn exit(&self, code: i32) -> !  {
        if code != 0 {
            self.cur_thread().stack().read_only_frame().print_debug_info();
            panic!()
        }
        exit(code);
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
            if !self.cur_thread().is_parked() {
                match self.program.get_ins(*self.ip()) {
                    None => {
                        println!("cannot find function 'main'");
                        self.exit(-1);
                    },
                    Some((ins, skip)) => {
                        self.perform_action(ins, skip);
                    },
                }
            } else {
                self.run_scheduler();
            }

        }
    }

    fn perform_action(&mut self, ins: Instruction, skip: usize) {
        let mut control_change = false;
        match ins {
            Instruction::Store(ptr) => {
                vm_debug!("Store {}", ptr);
                let obj = self.do_pop();
                let frame = self.cur_thread_mut().stack_mut().active_frame();
                frame.store(&obj, ptr);
            },
            Instruction::LoadConstInt(value) => {
                vm_debug!("LoadConstInt({})", value);
                let obj = self.allocate_and_assign_int(value);
                self.do_push(obj);
            },
            Instruction::LoadConstFloat(value) => {
                let obj = self.allocate_and_assign_float(value);
                self.do_push(obj);
            },
            Instruction::LoadConstStr(ptr) => {
                let the_str = self.read_string_or_exit(ptr);
                let obj = ObjRef::String(None, the_str);
                self.do_push(obj);
            },
            Instruction::LoadVar(ptr) => {
                vm_debug!("LoadVar({})", ptr);
                match self.cur_thread_mut().stack_mut().active_frame().get(ptr) {
                    Some(obj) => self.do_push(obj),
                    None => {
                        println!("no variable at stack index {}", ptr);
                        self.exit(-1);
                    }
                }
            },
            Instruction::CreateObject => {
                let obj_name = self.do_pop();
                let mut obj = self.heap.allocate(AllocType::Object);

                match (obj_name, &mut obj) {
                    (ObjRef::String(_, ref the_name), ObjRef::Object(_, ref mut the_obj)) => {
                        let class_template = self.program.lookup_class_template(the_name).unwrap_or_else(|| panic!("class '{}' does not exist", the_name));
                        for (field_name, field_value) in class_template.predefined_fields.iter() {
                            let field_ref = match field_value {
                                FieldData::String(value) => {
                                    self.heap.allocate_str(&value)
                                },
                                FieldData::Int(value) => self.allocate_and_assign_int(*value),
                                FieldData::Float(value) => self.allocate_and_assign_float(*value),
                            };

                            the_obj.fields.insert(field_name, field_ref);
                        }
                    },
                    _ => panic!("object instantiation error")
                };

                self.do_push(obj);
            },
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
            Instruction::LoadMember => {
                let mut field_name = self.do_pop();
                let mut obj = self.do_pop();

                match (&mut obj, field_name) {
                    (ObjRef::Object(_, the_object), ObjRef::String(_, ref the_str)) => {
                        vm_debug!("loading member {}", the_str);

                        match the_object.fields.get(the_str) {
                            None => {
                                println!("error loading member: member does not exist ('{}')", the_str);
                                self.exit(-1);
                            },
                            Some(ref mut member) => {
                                let cloned_member = member.clone();
                                self.do_push(cloned_member);
                            },
                        }

                    },
                    _ => {
                        println!("cannot load member from {:?}", &obj);
                        self.exit(-1);
                    }
                }
            },
            Instruction::StoreMember => unsafe {
                let member_name = self.do_pop();
                let mut target_object = self.do_pop();
                let source = self.do_pop();

                match (&mut target_object, &member_name) {
                    (ObjRef::Object(_, the_obj), ObjRef::String(_, the_str)) => {
                        the_obj.fields.insert(the_str, source);
                    },
                    _ => {
                        println!("value does not have members");
                        self.exit(-1);
                    }
                }
            },
            Instruction::Call => {
                vm_debug!("debug CALL");
                let function_name = self.do_pop();
                let param_count = self.do_pop();

                match (&function_name, &param_count) {
                    (ObjRef::String(_, fun_name), ObjRef::Int(_, count_num)) => {
                        let mut params: Vec<ObjRef> = Vec::new();
                        for _ in 0..**count_num {
                            params.push(self.do_pop());
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
                                None => {
                                    println!("failed to find function '{}'", *fun_name);
                                    self.exit(-1);
                                },
                                Some(ptr) => {
                                    control_change = true;
                                    (*self.ip()) = *self.ip() + skip;
                                    //println!("calling {}", *fun_name);
                                    self.cur_thread_mut().stack_mut().push_new(*ptr, *fun_name);
                                    for (i, p) in (&params).into_iter().enumerate() {
                                        self.cur_thread_mut().stack_mut().active_frame().store(p, i as u16);
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
                let cond = self.do_pop();
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
                match self.cur_thread_mut().stack_mut().active_exe().pop_optional() {
                    None => {
                        //println!("returning with no values");
                        //self.cur_thread().stack().read_only_frame().print_debug_info();
                        if !self.cur_thread_mut().stack_mut().pop() {
                            exit(0)
                        }
                        //println!("returning with no values (DONE)");
                        //self.cur_thread().stack().read_only_frame().print_debug_info();
                    }
                    Some(value) => {
                        if !self.cur_thread_mut().stack_mut().pop() {
                            exit(0);
                        }
                        self.do_push(value);
                    }
                }

            },
            Instruction::Throw => {},
            Instruction::PostEvent => {},
            Instruction::WaitEvent => {},
            Instruction::Spawn => {
                match self.do_pop() {
                    ObjRef::String(_, ref function_name) => {
                        let new_tid = self.threads.len();
                        let mut new_coroutine = Coroutine::new(new_tid);
                        (*new_coroutine.stack_mut().ip()) = *self.program.lookup_function(function_name).unwrap_or_else(||{
                            println!("cannot find function '{}'", function_name);
                            self.exit(-1)
                        });
                        self.threads.push(new_coroutine);
                    }
                    bad_ref @ _ => {
                        println!("cannot spawn corountine with non-callable: {:?}", bad_ref);
                        self.exit(-1);
                    }
                }
            },
            Instruction::SendAsync => {
                let obj = self.do_pop();

                match obj {
                    ObjRef::Object(_, ref object) => {
                        if object.fields.contains_key("status") && object.fields.contains_key("body") {
                            let status_ref = object.fields.get("status").unwrap();
                            let body_ref = object.fields.get("body").unwrap();
                            let id_ref = object.fields.get("requestId").unwrap();

                            match (status_ref, body_ref, id_ref) {
                                (ObjRef::Int(_, status), ObjRef::String(_, body), ObjRef::Int(_, id)) => {
                                    if let Some(Event::HttpEvent(_, mut request)) = self.events_in_progress.remove(id) {

                                        let mut response = HttpResponse::new();

                                        response.status = **status;
                                        response.body = String::from(*body);
                                        response.headers.insert(String::from("Content-Type"), String::from("text/plain; charset=UTF-8"));

                                        request.connection.write(&response.to_bytes());
                                        request.connection.flush();

                                    }
                                }
                                _ => {
                                    println!("can't send that");
                                    self.exit(-1);
                                }
                            }
                        }
                    },
                    bad_ref @ _ => {
                        println!("cannot send {:?}", bad_ref);
                        self.exit(-1);
                    }
                }

            }
            Instruction::ListenAsync => {
                let listen_template = self.do_pop();
                if let ObjRef::Object(_, ref object) = listen_template {
                    self.event_manager.register_listener(*object, self.active_thread_id);
                    self.cur_thread_mut().park();
                } else {
                    println!("cannot register an event listener with {:?}", listen_template);
                    self.exit(-1);
                }
            }
            Instruction::Not => {
                let obj = self.do_pop();
                match obj {
                    ObjRef::Int(_, ref v) => {
                        let result = self.allocate_and_assign_int(!(**v));
                        self.do_push(result);
                    },
                    _ => {
                        let result = self.allocate_and_assign_int(1);
                        self.do_push(result);
                    }
                }
            },
            Instruction::XOr => {int_only_binop!(^, self)},
            Instruction::BAnd => {int_only_binop!(&, self)},
            Instruction::BOr => {int_only_binop!(|, self)},
            Instruction::LeftShift => {int_only_binop!(<<, self)},
            Instruction::RightShift => {int_only_binop!(>>, self)},
            Instruction::URightShift => {int_only_binop!(<<, self)},
            Instruction::Modulo => {int_only_binop!(%, self)},
            Instruction::BCompliment => {},
            Instruction::Dup => {
                let tos = self.cur_thread_mut().stack_mut().active_exe().peek().clone();
                self.do_push(tos);
            },
            Instruction::Consume => {
                self.cur_thread_mut().stack_mut().active_exe().pop_optional();
            },
            Instruction::SwapTOS2WithTOS3 => {
                self.cur_thread_mut().stack_mut().active_exe().swap_from_end(1, 2);
            }
        }

        if !control_change {
            (*self.ip()) += skip;
        }
    }

    pub fn get_event_sender(&self) -> Sender<Event>{
        self.event_manager.get_new_sender()
    }

    fn run_scheduler(&mut self) {
        self.advance_thread(); //ensure scheduler fairness -> always advance thread
        self.event_manager.load_events();
        let event = self.event_manager.find_event_for_thread(self.cur_thread().get_thread_id());
        if let Some(event) = event {
            let mut target_mem = self.heap.allocate(AllocType::Object);
            if let ObjRef::Object(_, ref mut object) = &mut target_mem {
                match &event {
                    Event::AllThreadsFinished => {},
                    Event::HttpEvent(event_id, request) => {
                        let method = self.heap.allocate_str(&request.method);
                        let path = self.heap.allocate_str(&request.path);
                        let id = self.allocate_and_assign_int(*event_id);
                        object.fields.insert("method", method);
                        object.fields.insert("path", path);
                        object.fields.insert("id", id);

                        self.events_in_progress.insert(*event_id, event);
                    },
                }
            }
            self.do_push(target_mem); //listen expr leaves this with stack
             self.cur_thread_mut().unpark();
        }
    }

    fn advance_thread(&mut self) {
        if self.threads.len() == 1 {
            return;
        }

        if self.active_thread_index == self.threads.len() - 1 {
            self.active_thread_index = 0;
        } else {
            self.active_thread_index += 1;
        }

        self.active_thread_id = self.cur_thread().get_thread_id();
    }

    #[inline]
    fn do_concat(&mut self) {
        vm_debug!("Concat");
        let first = self.do_pop();
        let second = self.do_pop();

        match (&second, &first) {
            (ObjRef::String(_, left), ObjRef::String(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Int(_, left), ObjRef::String(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Float(_, left), ObjRef::String(_, right)) => concat_branch!(self, left, right),
            (ObjRef::String(_, left), ObjRef::Int(_, right)) => concat_branch!(self, left, right),
            (ObjRef::String(_, left), ObjRef::Float(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Float(_, left), ObjRef::Float(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Int(_, left), ObjRef::Int(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Float(_, left), ObjRef::Int(_, right)) => concat_branch!(self, left, right),
            (ObjRef::Int(_, left), ObjRef::Float(_, right)) => concat_branch!(self, left, right),
            _ => {
                self.cur_thread_mut().stack_mut().active_frame().print_debug_info();
                println!("concat type error: {:?} and {:?} cannot be concatenated ", second, first);
                exit(0);
            }
        }
    }

    fn read_string_or_exit(&self, ptr: u32) -> &'program str {
        return match self.program.read_str(ptr) {
            Ok(the_str) => the_str,
            Err(_) => {
                println!("error reading string at {}", ptr);
                self.exit(-1);
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



