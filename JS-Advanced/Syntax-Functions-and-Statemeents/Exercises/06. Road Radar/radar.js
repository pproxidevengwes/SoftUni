function radar(speed, area) {
    let speedLimit = 0;
    let diff = 0;
    let result = '';

    switch (area) {
        case 'motorway':
            speedLimit = 130;
            break;
        case 'interstate':
            speedLimit = 90;
            break;
        case 'city':
            speedLimit = 50;
            break;
        case 'residential':
            speedLimit = 20;
            break;
    }
  
    if (speed <= speedLimit) {
        result = `Driving ${speed} km/h in a ${speedLimit} zone`;
    } else {
        diff = speed - speedLimit;
        result = `The speed is ${diff} km/h faster than the allowed speed of ${speedLimit}`;

        if (diff <= 20) {
            result = result.concat(' - speeding');
        } else if (diff <= 40) {
            result = result.concat(' - excessive speeding');
        } else {
            result = result.concat(' - reckless driving');
        }
    }
    console.log(result);
}

radar(40, "city");
radar(21, "residential");
radar(120, "interstate");
radar(200, "motorway");
