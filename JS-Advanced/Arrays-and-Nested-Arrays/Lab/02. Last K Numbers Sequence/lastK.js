function solve(n, k) {
    let arr = [1];

    for (let i = 1; i < n; i++) {
        arr[i] = 0;
        for (let j = i; j > i - k; j--) {
            if (arr[j - 1] !== undefined) {
                arr[i] += arr[j - 1];
            }
        }
    }
    return arr;
}

console.log(solve(6, 3));
console.log(solve(8, 2));
