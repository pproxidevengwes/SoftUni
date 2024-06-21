function stars(size) {
    let result = '';
    for (let i = 1; i <= size; i++) {
        for (let j = 1; j <= size; j++) {
            result += '* ';
        }
        result += '\n';
    }
    console.log(result);
}

stars(5);
