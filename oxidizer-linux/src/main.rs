use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;

fn main() -> io::Result<()> {
    println!("Hello, world!");

    let mut f = File::open("../compiler/example.out")?;

    let mut buffer = [0; 3];
    f.read(&mut buffer);

    if [0xF, 0xE, 0x0] != buffer {
        println!("Didn't read a valid oxidizer bytecode file");
        exit(-1);
    }

    let mut buffer = [0; 12];
    f.read(&mut buffer);


    let (header_seg, string_seg, text_seg) = read_segment_offsets(&buffer);

    println!("Read segment data from the file.");
    println!("Headers: {}, Strings: {}, Text: {}", header_seg, string_seg, text_seg);

    Ok(())
}

fn read_segment_offsets(buffer: &[u8; 12]) -> (u32, u32, u32) {
    let header_seg = bytes_to_u32(&buffer[0..4]);
    let string_seg = bytes_to_u32(&buffer[4 .. 8]);
    let text_seg = bytes_to_u32(&buffer[8 .. 12]);

    (header_seg, string_seg, text_seg)
}

fn bytes_to_u32(bytes: &[u8]) -> u32 {
    ((bytes[0] as u32) << 24) |
    ((bytes[1] as u32) << 16) |
    ((bytes[2] as u32) << 8)  |
    (bytes[3] as u32)
}
