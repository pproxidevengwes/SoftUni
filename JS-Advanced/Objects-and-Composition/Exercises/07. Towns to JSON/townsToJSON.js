function solve(input) {
    const result = [];

    for (let i = 1; i < input.length; i++) {
        let [space, town, latitude, longitude] = input[i].split('|');
        result.push({
            'Town': town.trim(),
            'Latitude': Number(Number(latitude.trim()).toFixed(2)),
            'Longitude': Number(Number(longitude.trim()).toFixed(2))
        });
    }

    return JSON.stringify(result);
}

console.log(solve(
    ['| Town | Latitude | Longitude |',
        '| Sofia | 42.696552 | 23.32601 |',
        '| Beijing | 39.913818 | 116.363625 |'])
);
