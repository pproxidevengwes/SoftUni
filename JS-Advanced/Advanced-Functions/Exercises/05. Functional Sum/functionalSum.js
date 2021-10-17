function add(num) {
    let sum = num;

    function add(num2) {
        sum += num2;
        return add;
    }
    
    add.toString = () => { return sum };
    return add;
}

console.log(add(4)(5)(-3));
