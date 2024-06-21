function pie(pies, startPie, endPie) {
    const start = pies.indexOf(startPie);
    const end = pies.indexOf(endPie);

    const result = pies.slice(start, end + 1);

    return result;
}

console.log(pie(['Pumpkin Pie',
    'Key Lime Pie',
    'Cherry Pie',
    'Lemon Meringue Pie',
    'Sugar Cream Pie'],
    'Key Lime Pie',
    'Lemon Meringue Pie')
);

console.log(pie(['Apple Crisp',
    'Mississippi Mud Pie',
    'Pot Pie',
    'Steak and Cheese Pie',
    'Butter Chicken Pie',
    'Smoked Fish Pie'],
    'Pot Pie',
    'Smoked Fish Pie')
);
