function solve(arr) {
    let current = 1;
    let result = [];
  
    for (let command of arr) {
        if (command == 'add') {
            result.push(current);
        } else if (command == 'remove') {
            result.pop();
        }
        current++;
    }
  
    if (result.length) {
        console.log(result.join('\n'));

    } else {
        console.log('Empty')
    }
}

solve(['add',
    'add',
    'add',
    'add']
);
console.log('---')

solve(['add',
    'add',
    'remove',
    'add',
    'add']
);
console.log('---')

solve(['remove',
    'remove',
    'remove']
);
