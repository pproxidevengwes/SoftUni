-- Lab --
CREATE DATABASE `gamebar`;
USE `gamebar`;

#01. Create Tables
CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`first_name` VARCHAR(50),
`last_name` VARCHAR(50)
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `products`(
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`name` VARCHAR(50) NOT NULL,
`category_id` INT
);

#02. Insert Data in Tables
INSERT INTO `employees` (`first_name`, `last_name`) VALUES
	('Ivan', 'Petrov'),
	('Pencho', 'Slaveikov'),
	('Mitko', 'Ivanov');
    
#03. Alter Table
ALTER TABLE `employees`
add `middle_name` VARCHAR(50) not null;

#04. Adding Constraints
ALTER TABLE `products`
ADD CONSTRAINT `cat_fk`
FOREIGN KEY (`category_id`)
REFERENCES `categories` (`id`)
ON UPDATE NO ACTION
ON DELETE NO ACTION;

#05. Modifying Columns
ALTER TABLE `employees`
CHANGE COLUMN `middle_name` `middle_name`  VARCHAR(100);
