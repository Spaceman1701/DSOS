class Foobar {
    def constructor(name) {
        println(name);
    }

    def anotherMethod() {
        println("wow, you called another method!");
    }
}

def main() {
    f = new Foobar("this is a class method!");
    f.anotherMethod();
}