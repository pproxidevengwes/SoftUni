class Bank {
    constructor(bankName) {
        this._bankName = bankName;
        this.allCustomers = [];
    }

    newCustomer(customer) {
        if (this.allCustomers.some(c => c.personalId == customer.personalId)) {
            throw new Error(`${customer.firstName} ${customer.lastName} is already our customer!`);
        }
        const result = Object.assign({}, customer)
        customer.totalMoney = 0;
        customer.transactions = [];
        this.allCustomers.push(customer);
        return result;
    }

    depositMoney(personalId, amount) {
        if (!this.allCustomers.some(c => c.personalId == personalId)) {
            throw new Error('We have no customer with this ID!');
        }
        const customer = this.allCustomers.filter(c => c.personalId == personalId)[0];
        customer.totalMoney += amount;
        customer.transactions.unshift(`${customer.transactions.length + 1}. ${customer.firstName} ${customer.lastName} made deposit of ${amount}$!`);
        return `${customer.totalMoney}$`;
    }

    withdrawMoney(personalId, amount) {
        if (!this.allCustomers.some(c => c.personalId == personalId)) {
            throw new Error('We have no customer with this ID!');
        }
        const customer = this.allCustomers.filter(c => c.personalId == personalId)[0];

        if (customer.totalMoney >= amount) {
            customer.totalMoney -= amount;
            customer.transactions.unshift(`${customer.transactions.length + 1}. ${customer.firstName} ${customer.lastName} withdrew ${amount}$!`);
            return `${customer.totalMoney}$`;
        }
        throw new Error(`${customer.firstName} ${customer.lastName} does not have enough money to withdraw that amount!`)
    }

    customerInfo(personalId) {
        if (!this.allCustomers.some(c => c.personalId == personalId)) {
            throw new Error('We have no customer with this ID!');
        }
        const customer = this.allCustomers.filter(c => c.personalId == personalId)[0];
        let output = [`Bank name: ${this._bankName}`,
        `Customer name: ${customer.firstName} ${customer.lastName}`,
        `Customer ID: ${customer.personalId}`,
        `Total Money: ${customer.totalMoney}$`,
            'Transactions:'];
        customer.transactions.forEach(t => output.push(t));
        return output.join('\n');
    }
}


let bank = new Bank('SoftUni Bank');

console.log(bank.newCustomer({ firstName: 'Svetlin', lastName: 'Nakov', personalId: 6233267 }));
console.log(bank.newCustomer({ firstName: 'Mihaela', lastName: 'Mileva', personalId: 4151596 }));

bank.depositMoney(6233267, 250);
console.log(bank.depositMoney(6233267, 250));
bank.depositMoney(4151596, 555);

console.log(bank.withdrawMoney(6233267, 125));

console.log(bank.customerInfo(6233267));
