function solution(){
    class Employee{
        constructor(name, age){
            if(new.target == Employee){
                throw new Error("Cannot initializa abstract class Employee.")
            }
            this.name = name;
            this.age = age;
            this.salary = 0;
            this.tasks = [];
            this.currentTask = 0;
            this.dividend = 0;
        }

        work(){
            console.log(this.tasks[this.currentTask]);
            this.currentTask ++;
            if(this.currentTask > this.tasks.length -1){
                this.currentTask = 0;
            }

        }

        collectSalary(){
            console.log(`${this.name} received ${this.salary + this.dividend} this month.`);
        }
    };

    class Junior extends Employee{
        constructor(name, age){
            super(name, age);
            this.tasks = [`${this.name} is working on a simple task.`];
        }

    }

    class Senior extends Employee{
        constructor(name, age){
            super(name, age);
            this.tasks = [`${this.name} is working on a complicated task.`,
            `${this.name} is taking time off work.`,
            `${this.name} is supervising junior workers.`
            ];
        }
    }

    class Manager extends Employee{
        constructor(name, age){
            super(name, age);
            this.tasks = [`${this.name} scheduled a meeting.`,
            `${this.name} is preparing a quarterly report.`];
        }
    }

    return{
        Employee,
        Junior,
        Senior,
        Manager
    }
}
