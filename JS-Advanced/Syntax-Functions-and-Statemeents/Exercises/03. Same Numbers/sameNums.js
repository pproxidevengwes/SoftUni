function sameNums(n) {
    let numberStr = n.toString();
    let same = true;
    let sum = 0;

    for (let i = 0; i < numberStr.length; i++) {
        if (numberStr[i] !== numberStr[0]) {
            same = false;
        }
        sum += Number(numberStr[i]);
    }
    console.log(same);
    console.log(sum);
}

sameNums(1234);
sameNums(2222222);
