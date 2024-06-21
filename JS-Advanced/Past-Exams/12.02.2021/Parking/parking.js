class Parking {
    constructor(capacity) {
        this.capacity = capacity;
        this.vehicles = [];
    }

    addCar(carModel, carNumber) {
        if (this.vehicles.length == this.capacity) {
            throw new Error('Not enough parking space.');
        }
        this.vehicles.push({
            carModel,
            carNumber,
            payed: false
        });
        return `The ${carModel}, with a registration number ${carNumber}, parked.`;
    }

    removeCar(carNumber) {
        const car = this.vehicles.find(v => v.carNumber == carNumber);
        
        if (car == undefined) {
            throw new Error("The car, you're looking for, is not found.");
        } else {
            if (car.payed == false) {
                throw new Error(`${car.carNumber} needs to pay before leaving the parking lot.`)
            }
            this.vehicles = this.vehicles.filter(c => c != car);
            return `${car.carNumber} left the parking lot.`
        }
    }

    pay(carNumber) {
        const car = this.vehicles.find(v => v.carNumber == carNumber);
        
        if (car == undefined) {
            throw new Error(`${carNumber} is not in the parking lot.`);
        } else {
            if (car.payed) {
                throw new Error(`${car.carNumber}'s driver has already payed his ticket.`);
            }
            car.payed = true;
            return `${car.carNumber}'s driver successfully payed for his stay.`
        }
    }
    getStatistics(carNumber) {
        if (carNumber) {
            const car = this.vehicles.find(v => v.carNumber == carNumber);
            const payed = car.payed ? 'Has payed' : 'Not payed';
            return `${car.carModel} == ${car.carNumber} - ${payed}`
        }
        let stat = [`The Parking Lot has ${this.capacity - this.vehicles.length} empty spots left.`];
        this.vehicles.sort((a, b) => a.carModel.localeCompare(b.carModel)).forEach(c => {
            let payed = c.payed ? 'Has payed' : 'Not payed';
            stat.push(`${c.carModel} == ${c.carNumber} - ${payed}`);
        });
        return stat.join('\n');
    }
}

const parking = new Parking(12);

console.log(parking.addCar("Volvo t600", "TX3691CA"));
console.log(parking.getStatistics());

console.log(parking.pay("TX3691CA"));
console.log(parking.removeCar("TX3691CA"));
