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

class List {
    head = 0;
    tail = 0;

    def constructor(self) {

    }

    def insert(self, obj) {
        node = new ListNode(obj);
        if head == 0 {
            head = node;
            tail = node;
        } else {
            self.tail.next = node;
            node.prev = self.tail;
        }
    }

    def get(self, index) {
        if index == 0 {
            if self.head != 0 {
                return self.head.value;
            } else {
                return 0;
            }
        }
        i = 0;
        cur = self.head;
        while cur.next != 0 {
            i = i + 1;
            cur = cur.next;
            if i == index {
                return cur;
            }
        }
        return 0;
    }

    def remove(self, index) {
        cur = self.head;
        if cur == 0 {
            return 0;
        }
        if index == 0 {
            return internal_remove(self.head);
        }
        i = 0;
        while cur.next != 0 {
            i = i + 1;
            if index == i {
                return internal_remove(cur.next);
            }
        }
        return 0;
    }

    def internal_remove(self, node) {
        if node == self.head {
            self.head = node.next;
        }
        if node == self.tail {
            self.tail = node.prev;
        }

        if node.next != 0 and node.prev != 0 {
            next = node.next;
            node.prev.next = node.next;
            next.prev = node.prev;
        }

        return node.value;
    }
}

class ListNode {
    next = 0;
    prev = 0;
    value = 0;

    def constructor(value, self) {
        self.value = value;
    }
}

//Start: Program
def main() {
    println("starting server");

    spawn fooBarHandler;
    spawn helloWorldHandler;

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

def helloWorldHandler() {
    while true {
        request = listen new HttpEventTemplate("GET", "/hello");

        send new HttpResponse(200, "Hello, World", request);
    }
}