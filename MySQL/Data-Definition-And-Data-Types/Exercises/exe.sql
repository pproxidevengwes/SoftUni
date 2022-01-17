CREATE DATABASE `minions`;
USE `minions`;

#01. Create Tables
CREATE TABLE minions(
`id` INT PRIMARY KEY AUTO_INCREMENT ,
`name` VARCHAR(50) NOT NULL,
`age` INT 
);

CREATE TABLE towns(
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

#02. Alter Minions Table
SELECT * FROM minions;
ALTER TABLE minions
ADD COLUMN `town_id` INT,
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY (`town_id`)
REFERENCES towns (`id`);

#03. Insert Records in Both Tables
INSERT INTO towns (`id`, `name`) VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO minions (`id`, `name`, `age`, `town_id`) VALUES
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);

#04. Truncate Table Minions
TRUNCATE minions;

#05. Drop All Tables
DROP TABLE minions;
DROP TABLE towns;

#06. Create Table People
CREATE TABLE people(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` MEDIUMBLOB ,
`height` DECIMAL(5,2),
`weight` DECIMAL(5,2) ,
`gender` ENUM('m','f') NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT);

INSERT INTO people(`id`,`name`,`height`,`weight`,`gender`,`birthdate`) 
VALUES (1,'Gosho',NULL,NULL,'m','1997-06-14'),
(2,'Sasho',NULL,93,'m','1993-08-07'),
(3,'Pesho',1.65,88,'m','1994-09-25'),
(4,'Ivan',1.66,109,'m','1998-10-22'),
(5,'Stranimira',NULL,NULL,'f','1992-03-04');

#07. Create Table Users
CREATE TABLE users(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` DATETIME,
`is_deleted` ENUM('true','false'));

INSERT INTO users(`id`,`username`,`password`,`last_login_time`,`is_deleted`) 
VALUES (1,'martiiii','enigma','2021-12-21 18:51','false'),
(2,'krisi1010','pilesaszele','2020-08-24 14:22','true'),
(3,'jorji123','elparoles','2022-01-08 19:43','false'),
(4,'samchev1','krastavica',NULL,'true'),
(5,'anichkabanichka','666dtrf','2022-01-10 21:30','false');
                    
#08. Change Primary Key
ALTER TABLE users  
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users 
PRIMARY KEY(`id`,`username`);

#9. Set Default Value of a Field
ALTER TABLE users
MODIFY COLUMN `last_login_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

#10. Set Unique Field
ALTER TABLE users
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY users (`id`),
MODIFY COLUMN `username` VARCHAR(50) UNIQUE;

ALTER TABLE users
CHANGE `username` `username` VARCHAR(30) UNIQUE;

#11. Movies Database
CREATE DATABASE `movies`;
USE `movies`;

CREATE TABLE directors(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(45) NOT NULL,
`notes` TEXT
);

INSERT INTO directors(`director_name`,`notes`)
VALUES ('The Wachowskis',NULL),
('Emir Kusturica', NULL),
('Robert Rodriguez', NULL),
('Peter Jackson', NULL),
('Quentin Tarantino', NULL);


CREATE TABLE genres(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(45) NOT NULL,
`notes` TEXT
);

INSERT INTO genres(`genre_name`,`notes`)
VALUES ('sci-fi',NULL),
('romance',NULL),
('action',NULL),
('fantasy',NULL),
('crime',NULL);

CREATE TABLE categories(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(45) NOT NULL,
`notes` TEXT
);

INSERT INTO categories(`category_name`,`notes`)
VALUES ('Best Feature', NULL),
('Best Actor', NULL),
('Best Director', NULL),
('Best Actress', NULL),
('Best Costumes', NULL);

CREATE TABLE movies(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`title` VARCHAR(200) NOT NULL,
`director_id` INT NOT NULL,
`copyright_year` YEAR,
`length` VARCHAR(50),
`genre_id` INT,
`category_id` INT,
`rating` VARCHAR(50),
`notes` TEXT
); 

INSERT INTO movies 
VALUES (1, 'The Matrix', 1, '1999', '132 min', 1, 1, '8.7', NULL),
(2, 'Black Cat, White Ca', 2, '1998', '127 min', 2, 1, '8', NULL),
(3, 'Machete', 3, '2010', '105 min', 3, 2, '6.6', NULL),
(4, 'LOTR', 4, '2001', '178 min', 4, 2, '8.8', NULL),
(5, 'Kill Bill', 5, '2003', '111 min', 5, 4, '8', NULL);   

#12. Car Rental Database
CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE categories(
id INT PRIMARY KEY AUTO_INCREMENT,
category VARCHAR(30) NOT NULL,
daily_rate VARCHAR(30) NOT NULL,
weekly_rate VARCHAR(30) NOT NULL,
monthly_rate VARCHAR(30) NOT NULL,
weekend_rate VARCHAR(30) NOT NULL
);

CREATE TABLE cars(
id INT PRIMARY KEY AUTO_INCREMENT,
plate_number VARCHAR(20) NOT NULL UNIQUE,
make VARCHAR(30) NOT NULL,
model VARCHAR(30) NOT NULL,
car_year YEAR,
category_id INT NOT NULL,
doors INT,
picture BLOB,
car_condition TEXT,
available BIT 
);

CREATE TABLE employees(
id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(20) NOT NULL,
last_name VARCHAR(20) NOT NULL,
title VARCHAR(50) NOT NULL,
notes TEXT
);

CREATE TABLE customers(
id INT PRIMARY KEY AUTO_INCREMENT,
driver_licence_number VARCHAR(30) NOT NULL,
full_name VARCHAR(50) NOT NULL,
address VARCHAR(100) NOT NULL,
city VARCHAR(30) NOT NULL,
zip_code VARCHAR(50) NOT NULL,
notes TEXT
);

CREATE TABLE rental_orders(
id INT PRIMARY KEY AUTO_INCREMENT,
employee_id INT,
customer_id INT,
car_id INT,
car_condition VARCHAR(50),
tank_level INT,
kilometrage_start INT,
kilometrage_end INT,
total_kilometrage INT,
start_date DATE,
end_date DATE,
total_days INT,
rate_applied VARCHAR(20),
tax_rate INT,
order_status TEXT,
notes TEXT);

INSERT INTO categories(category, daily_rate, weekly_rate, monthly_rate, weekend_rate)
VALUES ('first', 3, 20, 70, 5),
('second', 4, 22, 80, 7),
('third', 5, 31, 115, 9);

INSERT INTO cars(plate_number, make, model, category_id, available)
VALUES ('B7183TT', 'Toyota', 'Yaris', 2, true),
('CA6969PK', 'Volkswagen', 'Passat', 2, true),
('CA4995AB', 'BMW', 'X5', 2, false);

INSERT INTO employees(first_name, last_name, title)
VALUES ('Gosho', 'Georgiev', 'Boss'),
('Ivan', 'Ivanov', 'title'),
('Mitko', 'Mitkov', 'title2');

INSERT INTO customers(driver_licence_number, full_name, address, city, zip_code)
VALUES (9863467856, 'Spas Spasov', 'BG', 'Sofia', '1111'),
(9896235835, 'Petko Petkov', 'BG', 'Sofia', '1408'),
(9875923536, 'Todor Todorov', 'BG', 'Sofia', '1113');

INSERT INTO rental_orders(employee_id, customer_id, car_id, start_date, end_date, rate_applied, tax_rate)
VALUES (2, 2, 1, '2022-01-15', '2022-01-16', 'daily_rate', 0.2),
(2, 3, 2, '2022-01-15', '2022-01-16', 'weekly_rate', 0.2),
(1, 1, 1, '2022-01-15', '2022-01-16', 'monthly_rate', 0.2);

#13. Basic Insert
CREATE DATABASE soft_uni;
USE soft_uni;

CREATE TABLE towns(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE addresses(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`address_text` VARCHAR(100) NOT NULL,
`town_id` INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (`town_id`) REFERENCES `towns`(`id`)
);

CREATE TABLE departments(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
); 

CREATE TABLE employees(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`middle_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`job_title`  VARCHAR(20),
`salary` DECIMAL (10,2),
`department_id` INT,
`hire_date` DATE,
`address_id` INT,
CONSTRAINT fk_employees_departments
FOREIGN KEY (`department_id`) REFERENCES `departments`(`id`),
CONSTRAINT fk_employees_addresses
FOREIGN KEY (`address_id`) REFERENCES `addresses`(`id`)
);

#JUDJE
INSERT INTO towns (`name`) VALUES
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');

INSERT INTO departments (`name`) VALUES
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');

INSERT INTO employees (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`, `address_id`) VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00, NULL),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00, NULL),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25, NULL),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00, NULL),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88, NULL);

#14. Basic Select All Fields
SELECT * FROM towns;
SELECT * FROM departments;
SELECT * FROM employees;

#15. Basic Select All Fields and Order Them
SELECT * FROM towns ORDER BY name;
SELECT * FROM departments ORDER BY name;
SELECT * FROM employees ORDER BY salary DESC;

#16. Basic Select Some Fields
SELECT name FROM `towns
ORDER BY name ASC;
SELECT name FROM departments
ORDER BY name ASC;
SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees`
ORDER BY salary DESC;

#17. Increase Employees Salary
UPDATE employees
SET salary = salary * 1.1;
SELECT salary FROM employees;

#18. Delete All Records
DELETE FROM `occupancies`;
