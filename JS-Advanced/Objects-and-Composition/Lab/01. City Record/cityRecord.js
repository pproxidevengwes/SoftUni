function record(name, population, treasury) {
    const city = {
        name,
        population,
        treasury
    };

    return city;
}

console.log(record(
    'Tortuga',
    7000,
    15000
));
