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

    pub fn new(buffer: Vec<u8>) -> Program {

        let segments = Program::read_segment_offsets(&buffer[3..]);

        let mut prog = Program {
            buffer,
            segments,
            headers: HeaderData {
                functions: HashMap::new()
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
                    println!("class field found");
                    index += 9
                }

                _ => {
                    println!("invalid header");
                    panic!()
                }
            }
        }
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
}


