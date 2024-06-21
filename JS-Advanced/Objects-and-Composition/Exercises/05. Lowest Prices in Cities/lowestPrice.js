function solve(data) {
    const products = {};

    for (const entry of data) {
        let [town, product, price] = entry.split(" | ");
        price = Number(price);

        if (!products[product]) {
            products[product] = {};
        }
        products[product][town] = price;
    }

    const result = [];

    for (const product in products) {
        let sorted = Object.keys(products[product]).sort(
            (a, b) => products[product][a] - products[product][b]
        );
        let town = sorted[0];
        result.push(`${product} -> ${products[product][town]} (${town})`);
    }

    return result.join("\n");
}

console.log(
    solve(['Sample Town | Sample Product | 1000',
        'Sample Town | Orange | 2',
        'Sample Town | Peach | 1',
        'Sofia | Orange | 3',
        'Sofia | Peach | 2',
        'New York | Sample Product | 1000.1',
        'New York | Burger | 10']
    )
);

