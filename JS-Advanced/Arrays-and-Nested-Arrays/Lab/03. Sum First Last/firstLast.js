function firstLast(arr){
    const first =Number(arr.shift());
    const last =Number(arr.pop());

    return first+last;
}

console.log(firstLast(['20', '30', '40']));
console.log(firstLast(['5', '10']));
