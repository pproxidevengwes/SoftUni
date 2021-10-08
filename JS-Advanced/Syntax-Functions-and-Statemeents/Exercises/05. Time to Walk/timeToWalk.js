function calculate(steps, footlength, speed) {
    let speedMps = speed * 1000 / 3600;
    let dist = steps * footlength;

    let breaks = Math.floor(dist / 500) * 60;
    let time = dist / speedMps + breaks;

    let hours = Math.floor(time / 3600);
    let min = Math.floor(time / 60);
    let sec = Math.ceil(time % 60);

    function result(n) {
        if (n < 10) {
            return '0' + n;
        } else {
            return n;
        }
    }
    console.log(`${result(hours)}:${result(min)}:${result(sec)}`);
}

calculate(4000, 0.60, 5);
calculate(2564, 0.70, 5.5);
