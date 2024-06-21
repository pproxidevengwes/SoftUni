function validate(x1, y1, x2, y2) {
    function getResult(x1, y1, x2, y2) {
        let dist = Math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2);
        return Number.isInteger(dist) ? 'valid' : 'invalid';
    }
  
    let result = `{${x1}, ${y1}} to {0, 0} is ${getResult(x1, y1, 0, 0)}\n` +
        `{${x2}, ${y2}} to {0, 0} is ${getResult(x2, y2, 0, 0)}\n` +
        `{${x1}, ${y1}} to {${x2}, ${y2}} is ${getResult(x1, y1, x2, y2)}`;
  
    console.log(result);

}

validate(3, 0, 0, 4);
validate(2, 1, 1, 1);
