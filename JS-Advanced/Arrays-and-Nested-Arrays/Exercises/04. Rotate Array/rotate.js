function rotate(arr, n) {
    for (let i = 1; i <= n; i++) {
        arr.unshift(arr.pop());
    }
    console.log(arr.join(' '));
}

rotate(['1', '2', '3', '4'], 2);
rotate(['Banana', 'Orange', 'Coconut', 'Apple'], 15);
