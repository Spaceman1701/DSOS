use std::net::TcpStream;
use std::io;
use std::time::Instant;
use std::io::Write;

fn main() -> io::Result<()> {
    let mut stream = TcpStream::connect("localhost:9000")?;
    let now = Instant::now();
    stream.write(b"GET /foo/bar HTTP/1.1\r\n\r\n\r\n");
    println!("{}", now.elapsed().as_secs_f64());


    Ok(())
}
