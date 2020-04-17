use std::io;
use std::io::prelude::*;
use std::fs::File;
use std::process::exit;
use program::Program;
use vm::VM;
use std::net::{TcpStream, TcpListener};
use std::io::Error;

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

    let mut listener = TcpListener::bind("0.0.0.0:9000").unwrap();
    for mut result in listener.incoming() {
        match result {
            Ok(ref mut stream) => {
                let mut tcp_buf = [0; 512];
                stream.read(&mut tcp_buf);

                let request_str = String::from_utf8_lossy(&mut tcp_buf);
                println!("{}", request_str);

                let response = "HTTP/1.1 200 OK\r\n\r\n<!DOCTYPE html><html><body>Hello, World</body></html>";

                stream.write(response.as_bytes());
                stream.flush().unwrap();
            },
            Err(_) => {panic!()},
        }
    }

    let program = Program::new(buffer);


    let mut vm = VM::new(&program);

    vm.execute();

    Ok(())
}

fn validate_file_header(buffer: &[u8]) -> bool {
    return buffer == [0xF, 0xE, 0x0]
}
