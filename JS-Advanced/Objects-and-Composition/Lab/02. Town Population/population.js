function population(towns) {
    const result = {};

    for (town of towns) {
        let tokens = town.split(" <-> ");
        let name = tokens[0];
        let population = Number(tokens[1]);
        
        if (result.hasOwnProperty(name)) {
            population += result[name];
        }
        result[name] = population;
    }

    for (let [key, value] of Object.entries(result)) {
        console.log(`${key} : ${value}`)
    }
}

population(['Istanbul <-> 100000',
'Honk Kong <-> 2100004',
'Jerusalem <-> 2352344',
'Mexico City <-> 23401925',
'Istanbul <-> 1000']);
