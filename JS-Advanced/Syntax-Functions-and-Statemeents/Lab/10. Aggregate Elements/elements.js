function elements(agr) {
    let sum = 0;
    let sumA = 0;
    let concat = '';

    for (let i = 0; i < agr.length; i++) {
        let element = agr[i];
        sum += element;
        sumA += element ** -1; // 1/agr[i]
        concat += element;
    }
    console.log(sum);
    console.log(sumA);
    console.log(concat);
}

elements([1, 2, 3]);
elements([2, 4, 8, 16]);
