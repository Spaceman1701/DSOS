use std::collections::HashMap;

#[derive(Debug)]
pub struct HttpRequest {
    pub method: String,
    pub path: String,
    pub headers: HashMap<String, String>,
    pub body: String
}

#[derive(Debug)]
pub struct HttpResponse {
    pub status: u32,
    pub headers: HashMap<String, String>,
    pub body: String
}

impl HttpResponse {
    pub fn new() -> HttpResponse {
        HttpResponse {
            status: 0,
            headers: HashMap::new(),
            body: String::new(),
        }
    }

    pub fn to_bytes(&self) -> Vec<u8> {
        format!("HTTP/1.1 {}\r\n\r\n{}", self.status, self.body).into_bytes()
    }
}