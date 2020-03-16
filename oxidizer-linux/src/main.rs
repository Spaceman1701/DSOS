use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;
use program::Program;

mod program;
mod instruction;

fn main() {
    let _ = load_file();
}

fn load_file() -> io::Result<()> {
    println!("Hello, world!");

    let mut f = File::open("../compiler/example.out")?;

    let mut buffer = Vec::new();
    match f.read_to_end(&mut buffer) {
        Ok(size) => println!("Read a {} byte file", size),
        Err(_) => exit(0),
    }

    if !validate_file_header(&buffer[0 .. 3]) {
        println!("didn't find a valid Oxidizer file");
        exit(-1);
    }

    let program = Program::new(buffer);

    println!("Text segment begins at {} bytes", program.segments.text);


    match program.read_str(0) {
        Ok(the_str) => println!("the first string is: {}", the_str),
        Err(_) => {},
    }



    Ok(())
}

fn validate_file_header(buffer: &[u8]) -> bool {
    return buffer == [0xF, 0xE, 0x0]
}
