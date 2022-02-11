CREATE SCHEMA stc;
USE stc;

-- DROP SCHEMA stc;

#01. Table Design
CREATE TABLE addresses (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (100) NOT NULL
);

CREATE TABLE clients (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`full_name` VARCHAR(50) NOT NULL,
`phone_number` VARCHAR(20) NOT NULL
);

CREATE TABLE categories (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(10) NOT NULL
);

CREATE TABLE cars (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`make` VARCHAR(20) NOT NULL,
`model` VARCHAR(20) NULL,
`year` INT NOT NULL DEFAULT 0,
`mileage` INT NULL DEFAULT 0,
`condition` CHAR NOT NULL,
`category_id` INT NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY (category_id)
REFERENCES categories(id)
);

CREATE TABLE courses (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`from_address_id` INT NOT NULL,
`start` DATETIME NOT NULL,
`bill` DECIMAL(10,2) DEFAULT 10,
`car_id` INT NOT NULL,
`client_id` INT NOT NULL,
CONSTRAINT fk_courses_addresses
FOREIGN KEY (from_address_id)
REFERENCES addresses(id),
CONSTRAINT fk_courses_clients
FOREIGN KEY (client_id)
REFERENCES clients(id),
CONSTRAINT fk_courses_cars
FOREIGN KEY (car_id)
REFERENCES cars(id)
);

CREATE TABLE drivers (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`age` INT NOT NULL,
`rating` FLOAT NULL DEFAULT 5.5
);

CREATE TABLE cars_drivers (
`car_id` INT NOT NULL,
`driver_id` INT NOT NULL,
PRIMARY KEY (car_id, driver_id),
CONSTRAINT fk_mapping_cars
FOREIGN KEY (car_id)
REFERENCES cars(id),
CONSTRAINT fk_mapping_drivers
FOREIGN KEY (driver_id)
REFERENCES drivers(id)
);

#02. Insert
INSERT INTO clients(full_name, phone_number)
SELECT concat(d.first_name, ' ', d.last_name),
concat('(088) 9999', d.id * 2)
FROM drivers AS d
WHERE d.id BETWEEN 10 AND 20;

#03. Update
UPDATE cars AS c SET `condition` = 'C'
WHERE c.mileage >= 800000 OR mileage IS NULL AND
`year` <= 2010 AND make != 'Mercedes-Benz';

#04. Delete
DELETE c FROM clients AS c 
LEFT JOIN courses AS co ON c.id = co.client_id
WHERE CHAR_LENGTH(full_name) > 3 AND
co.client_id IS NULL;

#05. Cars

SELECT make, model, `condition` FROM cars c
ORDER BY c.id;

#06. Drivers and Cars
SELECT d.first_name, d.last_name, c.make, c.model, c.mileage  FROM drivers AS d
JOIN cars_drivers AS cd ON cd.driver_id = d.id
JOIN cars AS c ON cd.car_id = c.id
WHERE c.mileage IS NOT NULL
ORDER BY mileage DESC, first_name;

#07. Number of courses
SELECT c.id AS 'car_id', c.make, c.mileage, 
count(co.car_id) AS 'count_of_courses',
round(avg(co.bill),2) AS 'avg_bill' FROM cars AS c
LEFT JOIN courses AS co ON co.car_id = c.id
GROUP BY c.id
HAVING count_of_courses != 2
ORDER BY `count_of_courses`  DESC, c.id;

#08. Regular clients
SELECT cl.full_name, count(co.car_id) AS 'count_of_cars',
sum(co.bill) AS 'total_sum' FROM clients AS cl
JOIN courses AS co ON co.client_id = cl.id
GROUP BY cl.id
HAVING cl.full_name LIKE '_a%' AND count_of_cars > 1
ORDER BY full_name;

#09. Full info for courses
SELECT a.`name`, (CASE 
WHEN hour(co.`start`) BETWEEN 6 AND 20 THEN 'Day'
WHEN hour(co.`start`) BETWEEN 21 AND 23 THEN 'Night'
WHEN hour(co.`start`) BETWEEN 0 AND 5 THEN 'Night'
END) AS 'day_time', co.bill, cl.full_name,
c.make, c.model, ca.`name` FROM addresses AS a
JOIN courses AS co ON co.from_address_id = a.id
JOIN clients AS cl ON co.client_id = cl.id
JOIN cars AS c ON co.car_id = c.id
JOIN categories AS ca ON c.category_id = ca.id
ORDER BY co.id;

#10. Find all courses by client’s phone number
DELIMITER $$
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20)) 
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(
SELECT count(*) FROM courses AS co
JOIN clients AS cl ON cl.id = co.client_id
WHERE cl.phone_number = phone_num
);
END $$
DELIMITER ;

DROP FUNCTION udf_courses_by_client;

SELECT udf_courses_by_client ('(803) 6386812') as `count`; 
SELECT udf_courses_by_client ('(831) 1391236') as `count`;
SELECT udf_courses_by_client ('(704) 2502909') as `count`;

#11. Full info for address
DELIMITER $$
CREATE PROCEDURE udp_courses_by_address(address_name VARCHAR (100))
BEGIN
SELECT a.`name`, cl.full_name, (CASE
WHEN co.bill <= 20 THEN 'Low'
WHEN co.bill <= 30 THEN 'Medium'
WHEN co.bill > 30 THEN 'High'
END) AS 'level of bill',
c.make, c.`condition`, ca.`name`
FROM addresses AS a
JOIN courses AS co ON co.from_address_id = a.id
JOIN clients AS cl ON co.client_id = cl.id
JOIN cars AS c ON co.car_id = c.id
JOIN categories AS ca ON c.category_id = ca.id
WHERE a.`name` = address_name
ORDER BY c.make, cl.full_name;
END $$
DELIMITER ;

DROP PROCEDURE udp_courses_by_address;

CALL udp_courses_by_address('700 Monterey Avenue');
CALL udp_courses_by_address('66 Thompson Drive');
