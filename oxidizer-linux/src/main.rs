use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;
use program::Program;
use vm::VM;
use std::net::{TcpStream, TcpListener};
use std::io::Error;
use crate::io_handler::IOHandler;

#[macro_use] mod debug_macros;
mod program;
mod instruction;
mod vm;
mod memory;
mod object;
mod call_stack;
mod http;
mod coroutine;
mod event_manager;
mod built_in;
mod io_handler;

fn main() {
    let _ = load_file();
}

fn load_file() -> io::Result<()> {
    let mut f = File::open("../compiler/example.out")?;

    let mut buffer = Vec::new();
    match f.read_to_end(&mut buffer) {
        Ok(size) => println!("Running Oxidizer Program {}", size),
        Err(_) => exit(0),
    }

    if !validate_file_header(&buffer[0 .. 3]) {
        println!("didn't find a valid Oxidizer file");
        exit(-1);
    }

    let program = Program::new(buffer);

    let tcp_listener = match TcpListener::bind("0.0.0.0:9000") {
        Ok(l) => l,
        _ => {
            println!("error binding to tcp socket");
            exit(-1);
        }
    };

    let mut vm = VM::new(&program);

    let io_sender = vm.get_event_sender();

    let io_thread_handler = IOHandler::new(tcp_listener, io_sender);
    io_thread_handler.start();

    vm.execute();

    Ok(())
}

fn validate_file_header(buffer: &[u8]) -> bool {
    return buffer == [0xF, 0xE, 0x0]
}
