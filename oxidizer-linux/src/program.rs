use std::str;
use std::str::Utf8Error;

use crate::instruction::Instruction;
use std::collections::HashMap;

pub struct Program {
    buffer: Vec<u8>,
    pub segments: SegmentMap,
    headers: HeaderData
}

pub struct SegmentMap {
    pub headers: usize,
    pub strings: usize,
    pub text: usize
}

pub struct HeaderData {
    functions: HashMap<String, usize>,
    classes: HashMap<String, ClassTemplate>
}

pub enum FieldData {
    String(String),
    Int(i64),
    Float(f64)
}

pub struct ClassTemplate {
    pub predefined_fields: HashMap<String, FieldData>
}

impl Program {
    fn read_segment_offsets(buffer: &[u8]) -> SegmentMap {
        let header_seg = Program::bytes_to_u32(&buffer[0..4]);
        let string_seg = Program::bytes_to_u32(&buffer[4 .. 8]);
        let text_seg = Program::bytes_to_u32(&buffer[8 .. 12]);

        SegmentMap {
            headers: header_seg as usize,
            strings: string_seg as usize,
            text: text_seg as usize
        }
    }

    fn bytes_to_u32(bytes: &[u8]) -> u32 {
        ((bytes[0] as u32) << 24) |
        ((bytes[1] as u32) << 16) |
        ((bytes[2] as u32) << 8)  |
        (bytes[3] as u32) << 0
    }

    fn bytes_to_u64(bytes: &[u8]) -> u64 {
        ((bytes[0] as u64) << 56) |
            ((bytes[1] as u64) << 48) |
            ((bytes[2] as u64) << 40)  |
            ((bytes[3] as u64) << 32)  |
            ((bytes[4] as u64) << 24) |
            ((bytes[5] as u64) << 16) |
            ((bytes[6] as u64) << 8)  |
            (bytes[7] as u64) << 0
    }

    pub fn new(buffer: Vec<u8>) -> Program {

        let segments = Program::read_segment_offsets(&buffer[3..]);

        let mut prog = Program {
            buffer,
            segments,
            headers: HeaderData {
                functions: HashMap::new(),
                classes: HashMap::new()
            }
        };

        prog.load_headers();
        prog
    }

    fn load_headers(&mut self) {
        let mut index: usize = self.segments.headers;

        while index < self.segments.strings {
            match self.buffer[index] {
                0x1 => {
                    let str_index = Program::bytes_to_u32(&self.buffer[index + 1 .. index + 5]);
                    let instruction = Program::bytes_to_u32(&self.buffer[index + 5.. index + 9]);

                    let string = self.read_str(str_index).unwrap_or_else(|_e| panic!());


                    let owned_string = String::from(string);

                    self.headers.functions.insert(owned_string, instruction as usize);

                    index += 9;
                }

                0x2 => {
                    let field_type = &self.buffer[index + 1];
                    let class_name = String::from(self.read_str(Program::bytes_to_u32(&self.buffer[index + 2 .. index + 6])).unwrap_or_else(|_e| panic!()));
                    let field_name = String::from(self.read_str(Program::bytes_to_u32(&self.buffer[index + 6.. index + 10])).unwrap_or_else(|_e| panic!()));
                    let field_value = Program::bytes_to_u64(&self.buffer[index + 10 .. index + 18]);

                    if !self.headers.classes.contains_key(&class_name) {
                        let class_template = ClassTemplate {
                            predefined_fields: HashMap::new(),
                        };
                        self.headers.classes.insert(class_name.clone(), class_template);
                    }

                    match field_type {
                        0 => {
                            let mut class_template = self.headers.classes.get_mut(&class_name).unwrap();
                            class_template.predefined_fields.insert(field_name, FieldData::Int(field_value as i64));
                        },
                        1 => {
                            let mut class_template = self.headers.classes.get_mut(&class_name).unwrap();
                            class_template.predefined_fields.insert(field_name, FieldData::Float(field_value as f64));
                        },
                        2 => {
                            let owned_field = self.read_owned_str(field_value as u32);
                            let mut class_template = self.headers.classes.get_mut(&class_name).unwrap();
                            class_template.predefined_fields.insert(field_name, FieldData::String(owned_field));
                        },
                        _ => panic!("illegal field type code: {}", field_type)
                    };


                    index += 18;
                }

                _ => {
                    println!("invalid header");
                    panic!()
                }
            }
        }
    }

    fn read_owned_str(&self, offset: u32) -> String {
        let borrowed_str = self.read_str(offset as u32).unwrap_or_else(|_e| panic!());
        String::from(borrowed_str)
    }

    pub fn read_str(&self, offset: u32) -> Result<&str, Utf8Error> {
        let mut end_index = 0;
        let strings_segment = &self.buffer[self.segments.strings + offset as usize..];
        for (index, byte) in (strings_segment).iter().enumerate() {
            if *byte == 0 {
                end_index = index;
                break;
            }
        }

        let start = self.segments.strings + offset as usize;
        let end = start + end_index as usize;

        return str::from_utf8(&self.buffer[start .. end]);
    }

    pub fn get_ins(&self, ptr: usize) -> Option<(Instruction, usize)> {
        Instruction::decode(&self.buffer[self.segments.text + ptr ..])
    }

    pub fn is_done(&self, ptr: usize) -> bool {
        self.segments.text + ptr >= self.buffer.len()
    }

    pub fn lookup_function(&self, name: &str) -> Option<&usize> {
        self.headers.functions.get(name)
    }

    pub fn lookup_class_template(&self, name: &str) -> Option<&ClassTemplate> {
        self.headers.classes.get(name)
    }
}
