function cooking(num, a, b, c, d, e) {
    let product = Number(num);
    let arr = [a, b, c, d, e];
  
    for (let i = 0; i < arr.length; i++) {
        switch (arr[i]) {
            case 'chop':
                product /= 2;
                break;
            case 'dice':
                product = Math.sqrt(product);
                break;
            case 'spice':
                product++;
                break;
            case 'bake':
                product *= 3;
                break;
            case 'fillet':
                product *= 0.8;
                break;
        }
        console.log(product);
    }
}

cooking('32', 'chop', 'chop', 'chop', 'chop', 'chop');
cooking('9', 'dice', 'spice', 'chop', 'bake', 'fillet');
