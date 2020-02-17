

def foobar(a, b) {
    c = a;
    while c < b {
        c++;
        if c == 7 {
            break;
        }
        if c == -1 {
            c = 1;
            continue;
        }
    }
    return c;
}

def main() {
    x = 5;
    y = 3;
    if x + y == 8 {
        foobar(x, 8);
    }
}