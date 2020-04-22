

def check_if_prime(n):
    i = 2
    while i < n / 2:
        if n % i == 0:
            return False
        i += 1
    return True


def main():
    n = 10000
    found_count = 0

    i = 4
    while i < n:
        if check_if_prime(i):
            found_count += 1
        i += 1

    print(found_count)


if __name__ == '__main__':
    main()
