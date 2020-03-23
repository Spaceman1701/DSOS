extern crate core;

use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;
use program::Program;
use vm::VM;

mod program;
mod instruction;
mod vm;

fn main() {
    let _ = load_file();
}

fn load_file() -> io::Result<()> {
    let mut f = File::open("../compiler/example.out")?;

    let mut buffer = Vec::new();
    match f.read_to_end(&mut buffer) {
        Ok(_) => println!("Running Oxidizer Program"),
        Err(_) => exit(0),
    }

    if !validate_file_header(&buffer[0 .. 3]) {
        println!("didn't find a valid Oxidizer file");
        exit(-1);
    }

    let program = Program::new(buffer);


    let mut vm = VM::new(&program);

    vm.execute();

    Ok(())
}

fn validate_file_header(buffer: &[u8]) -> bool {
    return buffer == [0xF, 0xE, 0x0]
}
