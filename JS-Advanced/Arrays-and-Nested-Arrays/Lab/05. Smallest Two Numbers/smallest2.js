function solve(arr) {
    let result = arr
        .sort((a, b) => a - b)
        .splice(0, 2)
        .join(' ');

    console.log(result);
}

solve([30, 15, 50, 5]);
solve([3, 0, 10, 4, 7, 3]);
