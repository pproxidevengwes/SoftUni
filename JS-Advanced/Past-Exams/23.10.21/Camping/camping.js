class SummerCamp {
    constructor(organizer, location) {
        this.location = location;
        this.organizer = organizer;
        this.priceForTheCamp = {
            'child': 150,
            'student': 300,
            'collegian': 500
        }
        this.listOfParticipants = [];
    }
 
    registerParticipant(name, condition, money) {
        if (!this.priceForTheCamp.hasOwnProperty(condition)) {
            throw new Error('Unsuccessful registration at the camp.');
        }
 
        if (money < this.priceForTheCamp[condition]) {
            return `The money is not enough to pay the stay at the camp.`;
        }
 
        for (let p of this.listOfParticipants) {
            if (p.name == name) {
                return `The ${name} is already registered at the camp.`;
            }
        }
 
        let newParticipant = {
            name,
            condition,
            power: 100,
            wins: 0
        }
        this.listOfParticipants.push(newParticipant);
        return `The ${name} was successfully registered.`;
    }
 
    unregisterParticipant(name) {
        const index = this.listOfParticipants.findIndex(p => p.name == name);
        
        if (index == -1)
            throw new Error(`The ${name} is not registered in the camp.`);
        else {
            this.listOfParticipants.splice(index, 1);
            return `The ${name} removed successfully.`;
        }
    }
 
    timeToPlay(typeOfGame, participant1, participant2) {
        if (typeOfGame == 'WaterBalloonFights') {
            const present = this.listOfParticipants.some(p => p.name == participant1 || p.name == participant2);
 
            if (!present) {
                throw new Error(`Invalid entered name/s.`);
            }
 
            const p1 = this.listOfParticipants.find(p => p.name == participant1);
            const p2 = this.listOfParticipants.find(p => p.name == participant2);
            
            if (p1.condition != p2.condition) {
                throw new Error(`Choose players with equal condition.`);
            }
 
            if (p1.power > p2.power) {
                p1.wins++
                return `The ${p1.name} is winner in the game ${typeOfGame}.`;
            } else if (p1.power < p2.power) {
                p2.wins++
                return `The ${p2.name} is winner in the game ${typeOfGame}.`;
            } else {
                return `There is no winner.`
            }
        }

        if (typeOfGame == 'Battleship') {
            const present = this.listOfParticipants.some(p => p.name == participant1);
 
            if (!present) {
                throw new Error(`Invalid entered name/s.`);
            }
            const p1 = this.listOfParticipants.find(p => p.name == participant1);
            p1.power += 20
            return `The ${participant1} successfully completed the game ${typeOfGame}.`;
        }
    }
 
    toString() {
        let sorted = this.listOfParticipants.sort((a, b) => b.wins - a.wins);
        let result = `${this.organizer} will take ${sorted.length} participants on camping to ${this.location}\n`.trim();
        sorted.forEach(p => result += `${p.name} - ${p.condition} - ${p.power} - ${p.wins}`.trim());
        return result;
    }
}
