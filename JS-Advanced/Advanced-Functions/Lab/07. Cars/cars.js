function solve(input) {
    let data = {}

    let obj = {
        create: (name, inherits, parentName) =>
            (data[name] = inherits ? Object.create(data[parentName]) : {}),

        set: (name, key, value) => (data[name][key] = value),

        print: name => {
            let entry = []
            for (let k in data[name]) {
                entry.push(`${k}:${data[name][k]}`)
            }
            console.log(entry.join(','));
        },
    }
    
    input.forEach(x => {
        let [c, name, key, value] = x.split(' ');
        obj[c](name, key, value);
    });
}


solve(['create c1',
    'create c2 inherit c1',
    'set c1 color red',
    'set c2 model new',
    'print c1',
    'print c2']);
