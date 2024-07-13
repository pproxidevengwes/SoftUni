function daysInMonth(month, year) {
    let result = new Date(year, month, 0);
    let days = result.getDate();
    console.log(days);
}

daysInMonth(2, 2020);
