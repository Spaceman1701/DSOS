import asyncio


def foo_bar_handler():
    return "Hi everyone, this website runs of Python..", 200


def hello_world_handler():
    return "Hello, World", 200


async def http_server(reader, writer):
    buffer = await reader.read(512)
    http_string = buffer.decode()
    http_pieces = http_string.split('\r\n')

    [method, path, _] = http_pieces[0].split(' ')
    print(method)
    print(path)
    if method == 'GET' and path == '/foo/bar':
        (response_body, code) = foo_bar_handler()
    elif method == 'GET' and path == '/hello':
        (response_body, code) = hello_world_handler()
    else:
        return

    writer.write(bytes(f"HTTP/1.1 {code} OK\r\nContent-Type: text/plain; charset=UTF-8\n\r\n{response_body}\r\n", 'utf-8'))
    await writer.drain()
    writer.close()
    await writer.wait_closed()


async def main(host, port):
    server = await asyncio.start_server(http_server, host, port)
    await server.serve_forever()


if __name__ == '__main__':
    asyncio.run(main('127.0.0.1', 9000))
