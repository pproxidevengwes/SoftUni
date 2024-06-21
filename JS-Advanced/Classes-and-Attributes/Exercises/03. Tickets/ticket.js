function solve(arr, sortValue) {
    class Ticket {
        constructor(destination, price, status) {
            this.destination = destination;
            this.price = price;
            this.status = status;
        }
    }

    let result = [];

    for (let el of arr) {
        let [destination, price, status] = el.split('|')
        let ticket = new Ticket(destination, Number(price), status);
        result.push(ticket);
    }

    if (sortValue == 'destination') {
        result.sort((a, b) => a.destination.localeCompare(b.destination));
    } else if (sortValue == 'price') {
        result.sort((a, b) => Number(a.price) - Number(b.price));
    } else {
        result.sort((a, b) => a.status.localeCompare(b.status));
    }

    return (result);
}

console.log(solve(['Philadelphia|94.20|available',
    'New York City|95.99|available',
    'New York City|95.99|sold',
    'Boston|126.20|departed'],
    'destination'
));
