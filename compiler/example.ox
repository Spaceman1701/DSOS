class Foobar {

    fooField = 0;

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
    println(10000);
    f = new Foobar("this is a class method!");
    f.anotherMethod("ethan's", "code sucks");

    println("f.fooField = ${f.fooField}");
    f.fooField = 10;
    println("f.fooField = ${f.fooField}");
}