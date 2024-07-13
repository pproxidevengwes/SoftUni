function solve() {
   document.querySelector("#btnSend").addEventListener("click", onClick);
 
   function onClick() {
     const text = JSON.parse(document.querySelector("#inputs textarea").value);
     const restaurants = {};
 
     for (const entry of text) {
       const tokens = entry.split(" - ");
       const restaurant = tokens[0];
       const workersData = tokens[1].split(", ");
 
       if (!restaurants[restaurant]) {
         restaurants[restaurant] = {};
         restaurants[restaurant].workers = {};
 
         restaurants[restaurant].avgSalary = function () {
           const salaries = Object.values(this.workers);
           const sum = salaries.reduce((a, b) => a + b, 0);
           return sum / salaries.length;
         };
         restaurants[restaurant].bestSalary = function () {
           return Math.max(...Object.values(this.workers));
         };
       }
 
       for (const dataEntry of workersData) {
         let [name, salary] = dataEntry.split(" ");
         salary = Number(salary);
 
         restaurants[restaurant].workers[name] = salary;
       }
     }
 
     const keys = Object.keys(restaurants);
     const bestRestaurant = keys.sort((a, b) => {
       return restaurants[b].avgSalary() - restaurants[a].avgSalary();
     })[0];
 
     document.querySelector("#bestRestaurant p").textContent = `Name: ${bestRestaurant} Average Salary: ${restaurants[bestRestaurant]
       .avgSalary()
       .toFixed(2)} Best Salary: ${restaurants[bestRestaurant].bestSalary().toFixed(2)}`;
 
     const workerKeys = Object.keys(restaurants[bestRestaurant].workers).sort(
       (a, b) => {
         return (
           restaurants[bestRestaurant].workers[b] -
           restaurants[bestRestaurant].workers[a]
         );
       }
     );
 
     let workersDataArray = [];
 
     for (const worker of workerKeys) {
       workersDataArray.push(
         `Name: ${worker} With Salary: ${restaurants[bestRestaurant].workers[worker]}`
       );
     }
 
     document.querySelector("#workers p").textContent =
       workersDataArray.join(" ");
   }
 }
