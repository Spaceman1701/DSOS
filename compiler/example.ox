class HttpRequest {
    path = 0;
    contentType = 0;
    body = 0;

    def constructor(path, contentType, body, self) {
        self.path = path;
        self.contentType = contentType;
        self.body = body;
    }
}


class HttpResponse {
    body = 0;
    contentType = 0;
    code = 0;

    def constructor(builder, self) {
        self.body = builder.body;
        self.contentType = builder.contentType;
        self.code = builder.code;
    }

    def toString(self) {
        return "HttpResponse - code: ${self.code}; contentType: ${self.contentType}; body: ${self.body}";
    }
}

class HttpResponseBuilder {
    body = 0;
    contentType = 0;
    code = 200;

    def constructor(self) {}

    def code(self, code) {
        self.code = code;
        return self;
    }

    def contentType(self, contentType) {
        self.contentType = contentType;
        return self;
    }

    def body(self, body) {
        self.body = body;
        return self;
    }

    def build(self) {
        return new HttpResponse(self);
    }
}

def main() {
    println("Welcome to the Oxidizer Web Server");

    response = new HttpResponseBuilder()
    response.code(404);
    response.contentType("text/plain");
    response.body("Hello, World");
    response = response.build();

    println(response.toString());
}