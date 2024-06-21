CREATE SCHEMA softuni_stores_system;
USE softuni_stores_system;
-- DROP SCHEMA softuni_stores_system;

#01. Table Design
CREATE TABLE towns (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (20) NOT NULL
);

CREATE TABLE addresses (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (50) NOT NULL,
`town_id` INT NOT NULL,
CONSTRAINT fk_addresses_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE stores (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (20) NOT NULL,
`rating` FLOAT NOT NULL,
`has_parking` TINYINT(1),
`address_id` INT NOT NULL,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id)
);

CREATE TABLE employees (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(15) NOT NULL,
`middle_name` CHAR,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(19,2) NOT NULL DEFAULT 0,
`hire_date` DATE NOT NULL,
`manager_id` INT,
`store_id` INT NOT NULL,
CONSTRAINT fk_employees_manager
FOREIGN KEY (manager_id)
REFERENCES employees(id),
CONSTRAINT fk_employees_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
);

CREATE TABLE categories (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL
);

CREATE TABLE pictures (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`url` VARCHAR(100) NOT NULL,
`added_on` DATETIME NOT NULL
);

CREATE TABLE products (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`best_before` DATE,
`price` DECIMAL(10,2) NOT NULL,
`description` TEXT,
`category_id` INT NOT NULL,
`picture_id` INT NOT NULL,
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_pictures
FOREIGN KEY (picture_id)
REFERENCES pictures(id)
);

CREATE TABLE products_stores (
`product_id` INT,
`store_id` INT,
PRIMARY KEY (product_id, store_id),
CONSTRAINT fk_mapping_products
FOREIGN KEY (product_id)
REFERENCES products(id),
CONSTRAINT fk_mapping_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
);

#02. Insert
INSERT INTO products_stores (product_id, store_id)
SELECT p.id, 1 FROM products AS p
WHERE (SELECT count(*) FROM products_stores AS ps 
WHERE p.id = ps.product_id) = 0;

#03. Update
UPDATE employees AS e 
JOIN stores AS s ON e.store_id = s.id
SET e.manager_id = 3, e.salary = e.salary - 500
WHERE year(hire_date) > 2003 AND
s.`name` NOT IN('Cardguard', 'Veribet');

#04. Delete
DELETE FROM employees 
WHERE manager_id IS NOT NULL AND salary >= 6000;

#05. Employees

SELECT first_name, middle_name, last_name, salary, hire_date
FROM employees AS e 
ORDER BY hire_date DESC;

#06. Products with old pictures
SELECT p.`name` ,p.price, p.best_before, 
concat(substr(p.`description`,10), '...')AS 'short_description', pc.url 
FROM products AS p
JOIN pictures AS pc ON pc.id= p.picture_id
WHERE char_length(p.`description`) > 100 AND 
year(pc.added_on) <= 2019 AND p.price > 20
ORDER BY p.price DESC;

#07. Counts of products in stores
SELECT s.`name`, count(pc.product_id) AS 'product_count',
round(avg(p.price),2) AS 'avg' FROM stores AS s
JOIN products_stores AS pc ON pc.store_id = s.id
JOIN products AS p ON pc.product_id = p.id
GROUP BY s.`name`
ORDER BY product_count DESC, `avg` DESC, s.id;

#08. Specific employee
SELECT concat_ws(' ', `first_name`, `last_name`) AS 'full_name',
s.`name`, a.`name`, salary FROM employees AS e 
JOIN stores AS s ON e.store_id = s.id
JOIN addresses AS a ON a.id = s.address_id
WHERE salary < 4000 AND a.`name` LIKE '%5%' AND
char_length(s.`name`) > 8 AND last_name LIKE '%n';

#09. Find all information of stores
SELECT reverse(s.`name`) AS 'reversed_name',
concat(upper(t.`name`), '-', a.name) AS 'full_address', 
count(e.store_id) AS 'employees_count' FROM stores AS s
JOIN employees AS e ON e.store_id = s.id
JOIN addresses AS a ON a.id = s.address_id
JOIN towns AS t ON t.id = a.town_id
GROUP BY s.`id`
HAVING count(e.store_id) >= 1
ORDER BY full_address;

#10. Find name of top paid employee by store name
DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50)) 
RETURNS VARCHAR (100)
DETERMINISTIC
BEGIN
RETURN(
SELECT concat(first_name, ' ', middle_name, '. ', last_name,
' works in store for ',TIMESTAMPDIFF(year,hire_date,'2020-10-18'),' years ')
FROM employees AS e
JOIN stores AS s ON e.store_id = s.id
WHERE store_name = s.`name`
GROUP BY e.id
ORDER BY max(salary) DESC
LIMIT 1
);
END $$
DELIMITER ;

DROP FUNCTION udf_top_paid_employee_by_store ;

SELECT udf_top_paid_employee_by_store('Stronghold') as 'full_info';
SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';

#11. Update product price by address 
DELIMITER $$
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN
UPDATE products AS p 
JOIN products_stores AS pc ON p.id = product_id
JOIN stores AS s ON s.id = pc.store_id
JOIN addresses AS a ON a.id = s.address_id
SET price = IF (a.`name` LIKE '0%' , price + 100, price + 200)
WHERE address_name = a.`name`;
END $$
DELIMITER ;

DROP PROCEDURE udp_update_product_price;

CALL udp_update_product_price('07 Armistice Parkway');
SELECT name, price FROM products WHERE id = 15;
CALL udp_update_product_price('1 Cody Pass');
SELECT name, price FROM products WHERE id = 17;


