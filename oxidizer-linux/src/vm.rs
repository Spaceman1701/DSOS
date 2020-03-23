
use program;
use std::process::exit;
use instruction::Instruction;
use core::borrow::Borrow;

pub struct VM<'a> {
    program: &'a program::Program,
    ip: usize,
    exe_stack: ExecutionStack<'a>
}

struct ExecutionStack<'a> {
    stack: Vec<ObjRef<'a>>
}

enum ObjRef<'a> {
    Str(&'a str),
    Int(i64),
    Float(f64)
}


impl <'program> VM<'program> {
    pub fn new(program: &'program program::Program) -> VM<'program> {
        VM {
            program: program,
            ip: 0 as usize,
            exe_stack: ExecutionStack {
                stack: Vec::new()
            }
        }
    }

    pub fn execute(&'program mut self) {
        let mut ip = self.ip;
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
        }
    }

    fn perform_action(&mut self, ins: Instruction, skip: usize) {
        let mut control_change = false;
        match ins {
            Instruction::Store(ptr) => {},
            Instruction::LoadConstInt(value) => {
                let obj = ObjRef::Int(value);
                self.exe_stack.stack.push(obj);
            },
            Instruction::LoadConstFloat(value) => {},
            Instruction::LoadConstStr(ptr) => {
                let the_str = self.read_string_or_exit(ptr);
                let obj = ObjRef::Str(the_str);
                self.exe_stack.stack.push(obj);
            },
            Instruction::LoadVar(ptr) => {},
            Instruction::CreateObject => {},
            Instruction::SliceList => {},
            Instruction::Add => {},
            Instruction::Sub => {},
            Instruction::Mul => {},
            Instruction::Div => {},
            Instruction::Mod => {},
            Instruction::Concat => {},
            Instruction::Pow => {},
            Instruction::LAnd => {},
            Instruction::LOr => {},
            Instruction::CompG => {},
            Instruction::CompL => {},
            Instruction::CompEq => {},
            Instruction::LoadMember => {},
            Instruction::StoreMember => {},
            Instruction::Call => {
                let function_name = self.exe_stack.stack.pop();
                let param_count = self.exe_stack.stack.pop();

                let t = function_name.and_then(|name| param_count.map(|count| (name, count)) )
                    .map(|(name, count)| {
                        match (name, count) {
                            (ObjRef::Str(fun_name), ObjRef::Int(count_num)) => {
                                let mut params: Vec<ObjRef> = Vec::new();
                                for _ in 0..count_num {
                                    params.push(self.exe_stack.stack.pop().unwrap());
                                }
                                if fun_name == "println" {
                                    match params[0] {
                                        ObjRef::Str(to_print) => {println!("{}", to_print)},
                                        ObjRef::Int(to_print) => {println!("{}", to_print)},
                                        ObjRef::Float(to_print) => {println!("{}", to_print)},
                                    }
                                }
                            }
                            _ => exit(-1)
                        }

                    });

            },
            Instruction::Jump(ptr) => {},
            Instruction::IfFalse(ptr) => {},
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

    fn read_string_or_exit(&self, ptr: u32) -> &'program str {
        return match self.program.read_str(ptr) {
            Ok(the_str) => the_str,
            Err(_) => {
                eprintln!("error reading string at {}", ptr);
                exit(-1)
            },
        }
    }
}