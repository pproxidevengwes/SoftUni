function calorie(list) {
    const result = {};
    
    for (i = 0; i < list.length; i += 2) {
        result[list[i]] = Number(list[i + 1]);
    }
    console.log(result);
}

calorie(['Yoghurt', '48', 'Rise', '138', 'Apple', '52']);

calorie(['Potato', '93', 'Skyr', '63', 'Cucumber', '18', 'Milk', '42']);
