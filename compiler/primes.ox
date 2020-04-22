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
        if self.head == 0 {
            self.head = node;
            self.tail = node;
        } else {
            self.tail.next = node;
            node.prev = self.tail;
            self.tail = node;
        }
    }

    def find_node(self, index) {
        i = 0;
        cur = self.head;
        while not (cur.next == 0) {
            if i == index {
                return cur;
            }
            cur = cur.next;
            i = i + 1;
        }
        return 0;
    }

    def get(self, index) {
        node = self.find_node(index);
        if node == 0 {
            return 0;
        }
        return node.value;
    }

    def set(self, index, value) {
        node = self.find_node(index);
        if node {
            node.value = value;
        }
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
    n = 5;
    prime_count = 0;
    i = 2;
    while i < n {

        if check_if_prime(i) {
            prime_count = prime_count + 1;
        }

        i = i + 1;
    }

    println(prime_count);
}


def check_if_prime(n) {
    i = 2;
    while i < (n / 2) {
        if n % i == 0 {
            return false;
        }
    }
    return true;
}

