function getFibonator() {
    let first = 0;
    let second = 1;

    function getNumber() {
        let next = first + second;
        second = first;
        first = next;
        return next;
    }

    return getNumber;
}

let fib = getFibonator();
console.log(fib());
console.log(fib());
console.log(fib());
console.log(fib());
console.log(fib());
console.log(fib());
console.log(fib());
console.log(fib());
