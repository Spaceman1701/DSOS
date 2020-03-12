use std::str;
use std::str::Utf8Error;


pub struct Program {
    buffer: Vec<u8>,
    pub segments: SegmentMap
}

pub struct SegmentMap {
    pub headers: usize,
    pub strings: usize,
    pub text: usize
}

impl Program {
    fn read_segment_offsets(buffer: &[u8]) -> SegmentMap {
        let header_seg = Program::bytes_to_u32(&buffer[0..4]);
        let string_seg = Program::bytes_to_u32(&buffer[4 .. 8]);
        let text_seg = Program::bytes_to_u32(&buffer[8 .. 12]);

        println!("{}, {}, {}", header_seg, string_seg, text_seg);

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

        Program {
            buffer,
            segments
        }
    }

    pub fn read_str(&self, offset: u32) -> Result<&str, Utf8Error> {
        let mut end_index = 0;
        let strings_segment = &self.buffer[self.segments.strings..];
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


}

