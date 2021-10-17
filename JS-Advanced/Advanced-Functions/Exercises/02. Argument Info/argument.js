function solve() {
    let data = {};

    for (let el of arguments) {

        if (!data[typeof(el)]) {
            data[typeof(el)] = 0;
        }
        data[typeof(el)] += 1;
        console.log(`${typeof(el)}: ${el}`);
    }

    let result = [];

    for (let el in data) {
        result.push([el, data[el]])
    }
    result.sort(function(a, b) {
        return b[1] - a[1];
    });


    for (let el of result) {
        console.log(`${el[0]} = ${el[1]}`)
    }
}


solve('cat', 42, function () { console.log('Hello world!'); });
