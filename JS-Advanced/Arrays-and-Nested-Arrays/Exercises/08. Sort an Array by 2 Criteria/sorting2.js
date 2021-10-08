function solve(arr) {
    let result = arr.sort((a, b) => {
        if (a.length > b.length) {
            return 1;
        } else if (a.length == b.length) {
            return a.localeCompare(b);
        } else {
            return -1;
        }
    })
    console.log(result.join('\n'));
}

solve(['alpha',
    'beta',
    'gamma']
);
console.log('---');

solve(['Isacc',
    'Theodor',
    'Jack',
    'Harrison',
    'George']
);
console.log('---');

solve(['test',
    'Deny',
    'omen',
    'Default']
);
