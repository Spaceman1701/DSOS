use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;
use std::string::FromUtf8Error;

fn main() {
    let _ = load_file();
}

fn load_file() -> io::Result<()> {
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

    f.seek(io::SeekFrom::Start(string_seg as u64));

    let mut byte_buffer = [0; 1];
    let mut utf_vec = vec![];

    f.read(&mut byte_buffer);
    while byte_buffer[0] != 0 {
        utf_vec.push(byte_buffer[0]);
        f.read(&mut byte_buffer);
    }

    let first_const_str = std::string::String::from_utf8(utf_vec);
    match first_const_str {
        Ok(the_str) => println!("{}", the_str),
        Err(_) => println!("couldn't read string constants"),
    }
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
