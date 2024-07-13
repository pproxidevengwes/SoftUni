function solve(input) {
    let result = [];

    for (let el of input) {
        let data = el.split(' ');

        if (data[0] == 'add') {
            result.push(data[1])
        } else if (data[0] == 'remove') {
            result = result.filter(el => el !== data[1]);
        } else {
            console.log(result.join(','))
        }
    }
}

solve(['add hello', 'add again', 'remove hello', 'add again', 'print']);
solve(['add pesho', 'add george', 'add peter', 'remove peter','print']);
