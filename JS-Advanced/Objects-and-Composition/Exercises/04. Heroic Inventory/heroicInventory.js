function solve(hero) {
    let result = [];

    for (const item of hero) {
        const [name, level, items] = item.split(" / ")
        itemsArr = items
            ? items.split(", ")
            : [];
        result.push({ name, level: Number(level), items: itemsArr });
    }
    
    return JSON.stringify(result);
}

console.log(solve(
    ['Isacc / 25 / Apple, GravityGun',
        'Derek / 12 / BarrelVest, DestructionSword',
        'Hes / 1 / Desolator, Sentinel, Antara'])
);
