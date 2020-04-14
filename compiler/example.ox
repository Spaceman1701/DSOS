class Foobar {
    def constructor(name) {
        println(name);
    }

    def anotherMethod(self, a, b) {
        println("in another method");
        println("${self.sayHi()} ${a} ${b}");
    }

    def sayHi() {
        return "hi";
    }
}

def main() {
    f = new Foobar("this is a class method!");
    f.anotherMethod(1, 2);
}