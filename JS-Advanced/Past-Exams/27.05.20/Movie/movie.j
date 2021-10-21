class Movie {
    constructor(movieName, ticketPrice) {
        this.movieName = movieName;
        this.ticketPrice = Number(ticketPrice);
        this.screenings = [];
        this.totalProfit = 0;
        this.totalTickets = 0;
    }

    newScreening(date, hall, description) {
        if (this.screenings.some(x => x.date === date && x.hall === hall)) {
            return `Sorry, ${hall} hall is not available on ${date}`;
        }
        else {
            this.screenings.push({ date, hall, description });
            return `New screening of ${this.movieName} is added.`
        }
    }

    endScreening(date, hall, soldTickets) {
        const screeningToRemove = this.screenings.find(x => x.date === date && x.hall === hall);

        if (screeningToRemove == undefined) {
            throw new Error(`Sorry, there is no such screening for ${this.movieName} movie.`)
        }
        let currentProfit = soldTickets * this.ticketPrice;
        this.totalProfit += currentProfit;
        this.totalTickets += soldTickets;
        let indexToRemove = this.screenings.indexOf(screeningToRemove);
        this.screenings.splice(indexToRemove, 1);
        return `${this.movieName} movie screening on ${date} in ${hall} hall has ended. Screening profit: ${currentProfit}`;


    }

    toString() {
        let result = [`${this.movieName} full information:`,
        `Total profit: ${this.totalProfit.toFixed(0)}$`,
        `Sold Tickets: ${this.totalTickets}`];

        if (this.screenings.length !== 0) {
            result.push(`Remaining film screenings:`);

            this.screenings.sort((a, b) => a.hall.localeCompare(b.hall));
            
            for (let i = 0; i < this.screenings.length; i++) {
                let currentElement = this.screenings[i];
                result.push(`${currentElement.hall} - ${currentElement.date} - ${currentElement.description}`);
            }
        }
        else {
            result.push(`No more screenings!`);
        }
        return result.join('\n');
    }
}


let m = new Movie('Wonder Woman 1984', '10.00');
console.log(m.newScreening('October 2, 2020', 'IMAX 3D', `3D`));
console.log(m.newScreening('October 3, 2020', 'Main', `regular`));
console.log(m.newScreening('October 4, 2020', 'IMAX 3D', `3D`));
console.log(m.endScreening('October 2, 2020', 'IMAX 3D', 150));
console.log(m.endScreening('October 3, 2020', 'Main', 78));
console.log(m.toString());

m.newScreening('October 4, 2020', '235', `regular`);
m.newScreening('October 5, 2020', 'Main', `regular`);
m.newScreening('October 3, 2020', '235', `regular`);
m.newScreening('October 4, 2020', 'Main', `regular`);
console.log(m.toString());
