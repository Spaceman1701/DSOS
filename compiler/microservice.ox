//Start: standard library (lol)

class HttpEventTemplate {
    method = 0;
    path = 0;

    def constructor(method, path, self) {
        self.method = method;
        self.path = path;
    }
}

class AllThreadsDoneEventTemplate {
    def constructor() {}
}

class HttpResponse {
    status = 0;
    body = 0;
    requestId = 0;

    def constructor(status, body, request, self) {
        self.status = status;
        self.body = body;

        self.requestId = request.id;
    }
}

//Start: Program
def main() {
    println("starting server");
    spawn fooBarHandler;
    listen new AllThreadsDoneEventTemplate(); //vm will send "all threads done" event when all coroutines exit
}


def fooBarHandler() {
    while true {
        request = listen new HttpEventTemplate("GET", "/foo/bar");

        println(request.path);
        println(request.method);

        send new HttpResponse(200, "Hi everyone, this website runs on Oxidizer", request);
    }
}