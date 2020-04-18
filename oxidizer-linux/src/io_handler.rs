use std::net::{TcpListener, TcpStream};
use std::thread;
use std::io::Read;
use std::sync::{mpsc, Mutex, Arc};

use httparse;
use std::collections::HashMap;
use httparse::{Request, Status, Header};
use crate::event_manager::{Event};
use std::process::exit;
use crate::http::HttpRequest;

pub struct HttpEventListener {
    method: String,
    path: String,
    thread_id: usize,
}

pub struct IOHandler {
    listener: TcpListener,
    sender: mpsc::Sender<Event>,
    count: i64,
}


impl IOHandler {

    pub fn new(listener: TcpListener, sender: mpsc::Sender<Event>) -> IOHandler {
        IOHandler {
            listener,
            sender,
            count: 0,
        }
    }

    pub fn start(mut self) {
        thread::spawn(move || {
            for incoming in self.listener.incoming() {
                let mut stream = incoming.unwrap();
                let buffer = IOHandler::read_from_stream(&mut stream);
                let mut headers = [httparse::EMPTY_HEADER; 64];
                let (request, size) = IOHandler::parse_http_request(&buffer, &mut headers);
                println!("http request: {:?}, {:?}", request.method ,request.path);
                let copied_request = IOHandler::build_http_request(&buffer, &request, size, stream);
                self.sender.send(Event::HttpEvent(self.count, copied_request));
                self.count += 1;
            }
        });
    }

    fn build_http_request(buffer: &[u8], request: &Request, size: usize, connection: TcpStream) -> HttpRequest {
        let body = String::from_utf8_lossy(&buffer[size..]);
        let actual_body_str:&str = body.split("\r\n").collect::<Vec<&str>>()[0];

        HttpRequest::from_request(request, String::from(actual_body_str), connection)
    }

    fn parse_http_request<'buffer>(buffer: &'buffer [u8; 2048], headers: &'buffer mut [Header<'buffer>]) -> (httparse::Request<'buffer, 'buffer>, usize) {
        let mut req = httparse::Request::new(headers);


        let res = req.parse(buffer);
        match res {
            Err(_) => {
                println!("httparse error");
                exit(-1);
            }
            Ok(Status::Partial) => {
                println!("request too big");
                exit(-1);
            }
            Ok(Status::Complete(size)) => {
                (req, size)
            }
        }
    }

    fn read_from_stream(stream: &mut TcpStream) -> [u8; 2048] {
        let mut tcp_buffer = [0; 2048];
        stream.read(&mut tcp_buffer);
        tcp_buffer
    }
}