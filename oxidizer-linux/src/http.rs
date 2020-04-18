use std::collections::HashMap;
use httparse::Request;
use std::net::TcpStream;

#[derive(Debug)]
pub struct HttpRequest {
    pub method: String,
    pub path: String,
    pub headers: HashMap<String, String>,
    pub body: Option<String>,
    pub connection: TcpStream,
}

#[derive(Debug)]
pub struct HttpResponse {
    pub status: i64,
    pub headers: HashMap<String, String>,
    pub body: String
}

impl HttpRequest {
    pub fn from_request(req: &Request, body: String, connection: TcpStream) -> HttpRequest {
        let mut result = HttpRequest {
            method: String::from(req.method.unwrap()),
            path: String::from(req.path.unwrap()),
            headers: HashMap::new(),
            body: Some(body),
            connection,
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
        let mut request = format!("HTTP/1.1 {}\r\n", self.status);

        for (name, value) in &self.headers {
            request.push_str(format!("{}: {}\n", name, value).as_str());
        }


        format!("{}\r\n{}\r\n", request, self.body).into_bytes()
    }
}