CREATE SCHEMA online_store;
USE online_store;
-- DROP SCHEMA online_store;

#01. Table Design
CREATE TABLE brands (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE categories (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE reviews (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`content` TEXT,
`rating` DECIMAL(10,2) NOT NULL,
`picture_url` VARCHAR(80) NOT NULL,
`published_at` DATETIME NOT NULL
);

CREATE TABLE products (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`price` DECIMAL(19,2) NOT NULL,
`quantity_in_stock` INT,
`description` TEXT,
`brand_id` INT NOT NULL,
`category_id` INT NOT NULL,
`review_id` INT,
CONSTRAINT fk_products_brands
FOREIGN KEY (brand_id)
REFERENCES brands(id),
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_reviews
FOREIGN KEY (review_id)
REFERENCES reviews(id)
);

CREATE TABLE customers (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR (20) NOT NULL,
`last_name` VARCHAR (20) NOT NULL,
`phone` VARCHAR(30) NOT NULL UNIQUE,
`address` VARCHAR(60) NOT NULL,
`discount_card` BIT NOT NULL DEFAULT FALSE
);

CREATE TABLE orders (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`order_datetime` DATETIME NOT NULL,
`customer_id` INT NOT NULL,
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(id)
);

CREATE TABLE orders_products (
`order_id` INT,
`product_id` INT,
CONSTRAINT fk_mapping_orders
FOREIGN KEY (order_id)
REFERENCES orders(id),
CONSTRAINT fk_mapping_products
FOREIGN KEY (product_id)
REFERENCES products(id)
);

#02. Insert
INSERT INTO reviews(content, picture_url, published_at, rating)
SELECT substring(p.`description`,1,15), reverse(p.`name`),
'2010-10-10', p.price/8 FROM products AS p
WHERE p.id >= 5;

#03. Update
UPDATE products AS p SET quantity_in_stock = quantity_in_stock - 5
WHERE quantity_in_stock >= 60 AND quantity_in_stock <= 70;

#04. Delete
DELETE c FROM customers AS c 
LEFT JOIN orders AS o ON o.customer_id = c.id
WHERE customer_id IS NULL;

#05. Categories
SELECT * from categories AS c
ORDER BY c.`name` DESC;

#06. Quantity
SELECT p.id, p.brand_id, p.`name`, p.quantity_in_stock
FROM products AS p
WHERE p.price > 1000 AND p.quantity_in_stock < 30
ORDER BY p.quantity_in_stock, p.id;

#07. Review
SELECT * FROM reviews 
WHERE content LIKE 'My%' AND char_length(content) > 61
ORDER BY rating DESC;

#08. First customers
SELECT concat_ws(' ', `first_name`, `last_name`) AS 'full_name',
address, o.order_datetime
FROM customers AS c
JOIN orders AS o ON o.customer_id = c.id
WHERE year(order_datetime) <= 2018
ORDER BY full_name DESC;

#09. Best categories
SELECT count(category_id) AS 'items_count', ca.`name`,
sum(p.quantity_in_stock) AS 'total_quantity'
FROM categories AS ca
JOIN products AS p ON p.category_id = ca.id
GROUP BY category_id
ORDER BY items_count DESC, total_quantity
LIMIT 5;

#10. Extract client cards count
DELIMITER $$
CREATE FUNCTION udf_customer_products_count(name VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(
SELECT count(product_id) 
FROM orders_products AS op
JOIN orders AS o ON o.id = op.order_id
JOIN customers AS cu ON cu.id = o.customer_id
WHERE cu.first_name LIKE name
);
END $$
DELIMITER ;

DROP FUNCTION udf_customer_products_count;

SELECT c.first_name,c.last_name, udf_customer_products_count('Shirley') as `total_products` FROM customers c
WHERE c.first_name = 'Shirley';

#11. Reduce price
DELIMITER $$
CREATE PROCEDURE udp_reduce_price (category_name VARCHAR(50))
BEGIN
UPDATE products AS p 
LEFT JOIN reviews AS r ON p.review_id = r.id
LEFT JOIN categories AS c ON p.category_id = c.id
SET p.price = p.price * 0.7
WHERE r.rating < 4 AND category_id IS NOT NULL;
END $$
DELIMITER ;

DROP PROCEDURE udp_reduce_price ;

CALL udp_reduce_price ('Phones and tablets');

