use std::collections::HashMap;
use httparse::Request;

#[derive(Debug)]
pub struct HttpRequest {
    pub method: String,
    pub path: String,
    pub headers: HashMap<String, String>,
    pub body: Option<String>
}

#[derive(Debug)]
pub struct HttpResponse {
    pub status: u32,
    pub headers: HashMap<String, String>,
    pub body: String
}

impl HttpRequest {
    pub fn from_request(req: &Request, body: String) -> HttpRequest {
        let mut result = HttpRequest {
            method: String::from(req.method.unwrap()),
            path: String::from(req.path.unwrap()),
            headers: HashMap::new(),
            body: Some(body)
        };

        for ref header in req.headers.iter() {
            let key = String::from(header.name);
            let value = String::from_utf8(header.value.to_vec()).unwrap();

            result.headers.insert(key, value);
        }

        result
    }
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