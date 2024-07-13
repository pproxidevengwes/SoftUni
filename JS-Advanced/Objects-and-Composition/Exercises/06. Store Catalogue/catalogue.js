function sort(data) {
    const catalogue = {};

    for (const entry of data) {
        let [product, price] = entry.split(" : ");
        price = Number(price);
        const initial = product[0];

        if (!catalogue[initial]) {
            catalogue[initial] = {};
        }
        catalogue[initial][product] = price;
    }
    const keys = Object.keys(catalogue).sort((a, b) => a.localeCompare(b));

    for (const key of keys) {
        console.log(key);
        Object.keys(catalogue[key])
            .sort((a, b) => a.localeCompare(b))
            .forEach((name) => console.log(`  ${name}: ${catalogue[key][name]}`));
    }
}

sort([
    "Appricot : 20.4",
    "Fridge : 1500",
    "TV : 1499",
    "Deodorant : 10",
    "Boiler : 300",
    "Apple : 1.25",
    "Anti-Bug Spray : 15",
    "T-Shirt : 10",
]);
