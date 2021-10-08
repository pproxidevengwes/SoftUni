function gcd(x, y) {
    let result = 1;
    for (let i = 1; i <= y; i++) {
        if (x % i == 0 && y % i == 0) {
            result = i;
        }
    }
    console.log(result);
}

gcd(2154, 458);
gcd(5, 15);
