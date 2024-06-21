function solve(input) {
    let data = {};

    input.forEach(line => {
        let [brand, model, carsProduced] = line.split(' | ');
        carsProduced = Number(carsProduced);
      
        if (!data.hasOwnProperty(brand)) {
            data[brand] = { [model]: carsProduced };
        } else {
            if (!data[brand].hasOwnProperty(model)) {
                data[brand][model] = carsProduced;
            } else {
                data[brand][model] += carsProduced;
            }
        }
    });
  
    for (let key in data) {
        console.log(key);
        let entries = Object.entries(data[key]);
        for (let kvp of entries) {
            console.log(`###${kvp[0]} -> ${kvp[1]}`);
        }
    }
}

solve(['Audi | Q7 | 1000',
    'Audi | Q6 | 100',
    'BMW | X5 | 1000',
    'BMW | X6 | 100',
    'Citroen | C4 | 123',
    'Volga | GAZ-24 | 1000000',
    'Lada | Niva | 1000000',
    'Lada | Jigula | 1000000',
    'Citroen | C4 | 22',
    'Citroen | C5 | 10']
);
