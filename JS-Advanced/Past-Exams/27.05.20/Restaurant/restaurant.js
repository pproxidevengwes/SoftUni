class Restaurant {
    constructor(budgetMoney) {
        this.budgetMoney = budgetMoney;
        this.menu = {};
        this.stockProducts = {};
        this.history = [];
    }

    loadProducts(products) {
        for (let product of products) {
            let [currProduct, currQuantity, currPrice] = product.split(' ');

            if (this.budgetMoney >= Number(currPrice)) {

                if (!this.stockProducts.hasOwnProperty(currProduct)) {
                    this.stockProducts[currProduct] = 0;
                }

                this.stockProducts[currProduct] += Number(currQuantity);
                this.budgetMoney -= currPrice;

                this.history.push(`Successfully loaded ${currQuantity} ${currProduct}`);
            } else {
                this.history.push(`There was not enough money to load ${currQuantity} ${currProduct}`);
            }
        }
        return this.history.join('\n');
    }

    addToMenu(meal, neededProducts, price) {
        if (!this.menu.hasOwnProperty(meal)) {
            //let [product, quantity] = neededProducts.split(' ');
            this.menu[meal] = {
                products: {},
                price: price
            };

            neededProducts.forEach((el) => {
                let [name, quantity] = el.split(' ');
                this.menu[meal].products[name] = Number(quantity);
            })

            let mealCount = Object.keys(this.menu).length;
            if (mealCount == 1) {
                return `Great idea! Now with the ${meal} we have 1 meal in the menu, other ideas?`;
            } else if (mealCount == 0 || mealCount >= 2) {
                return `Great idea! Now with the ${meal} we have ${mealCount} meals in the menu, other ideas?`;
            }
        } else {
            return `The ${meal} is already in the our menu, try something different.`;
        }

    }

    showTheMenu() {
        if (Object.keys(this.menu).length == 0) {
            return 'Our menu is not ready yet, please come later...';
        } else {
            let result = [];
            for (let meal of Object.keys(this.menu)) {
                result.push(`${meal} - $ ${this.menu[meal].price}`);
            }
            return result.join('\n');
        }
    }

    makeTheOrder(meal) {
        if (!this.menu[meal]) {
            return `There is not ${meal} yet in our menu, do you want to order something else?`;
        } else {
            let available = true
            let mealProducts = this.menu[meal].products;
            for (let currentProduct in mealProducts) {
                if (this.stockProducts.hasOwnProperty(currentProduct) && this.stockProducts[currentProduct] >= mealProducts[currentProduct]) {
                    this.stockProducts[currentProduct] -= mealProducts[currentProduct];
                }
                else {
                    available = false;
                }
            }

            if (available) {
                let currPrice = this.menu[meal].price;
                this.budgetMoney += currPrice;
                return `Your order (${meal}) will be completed in the next 30 minutes and will cost you ${currPrice}.`;
            } else {
                return `For the time being, we cannot complete your order (${meal}), we are very sorry...`
            }
        }
    }
}


let kitchen = new Restaurant(1000);
console.log(kitchen.loadProducts(['Banana 10 5', 'Banana 20 10', 'Strawberries 50 30', 'Yogurt 10 10', 'Yogurt 500 1500', 'Honey 5 50']));
