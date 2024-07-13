CREATE SCHEMA ruk_database;
USE ruk_database;
-- DROP SCHEMA ruk_database;

#01. Table Design
CREATE TABLE branches (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE employees (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(20) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`started_on` DATE NOT NULL,
`branch_id` INT,
CONSTRAINT fk_employees_branches
FOREIGN KEY (branch_id)
REFERENCES branches(id)
);

CREATE TABLE clients (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL UNIQUE,
`full_name` VARCHAR (50) NOT NULL,
`age` INT NOT NULL
);

CREATE TABLE employees_clients (
`employee_id` INT,
`client_id` INT,
CONSTRAINT fk_mapping_employees
FOREIGN KEY (employee_id)
REFERENCES employees(id),
CONSTRAINT fk_mapping_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE bank_accounts (
`id` INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
`account_number` VARCHAR (10) NOT NULL,
`balance` DECIMAL(10,2) NOT NULL,
`client_id` INT,
CONSTRAINT fk_bank_accounts_clients
FOREIGN KEY (client_id)
REFERENCES clients(id)
);

CREATE TABLE cards (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`card_number` VARCHAR (19) NOT NULL,
`card_status` VARCHAR(7) NOT NULL,
`bank_account_id` INT,
CONSTRAINT fk_cards_bank_accounts
FOREIGN KEY (bank_account_id)
REFERENCES bank_accounts(id)
);

#02. Insert
INSERT INTO cards(card_number, card_status, bank_account_id)
SELECT reverse(cl.full_name), 'Active', cl.id
FROM clients AS cl
WHERE id BETWEEN 191 AND 200;

#03. Update
UPDATE employees_clients AS ec SET employee_id = (
SELECT employee_id FROM (SELECT * FROM employees_clients) AS ec2
GROUP BY ec2.employee_id
ORDER BY count(ec2.client_id), employee_id
LIMIT 1
)
WHERE employee_id = client_id;

#04. Delete
DELETE e FROM employees AS e
LEFT JOIN employees_clients AS ec ON ec.employee_id = e.id
WHERE client_id IS NULL;

#05. Clients
SELECT id, full_name FROM clients
ORDER BY id;

#06. Newbies
SELECT id, 
concat_ws(' ', `first_name`, `last_name`) AS 'full_name',
concat('$', salary) AS salary , started_on FROM employees 
WHERE salary >= 100000 AND date(started_on) >= date('2018-01-01')
ORDER BY salary DESC, id;  

#07. Cards against Humanity
SELECT ca.id,
concat_ws(' : ', ca.card_number, cl.full_name) AS 'card_token'
FROM cards AS ca
JOIN bank_accounts AS ba ON ca.bank_account_id = ba.id
JOIN clients AS cl ON ba.client_id = cl.id
ORDER BY ca.id DESC;

#08. Top 5 Employees
SELECT concat_ws(' ', e.`first_name`, e.`last_name`) AS 'full_name', 
e.started_on, count(ec.client_id) AS 'count_of_clients'
FROM employees AS e
JOIN employees_clients AS ec ON ec.employee_id = e.id
GROUP BY e.id
ORDER BY count_of_clients DESC, e.id
LIMIT 5;

#09. Branch cards
SELECT b.`name`, count(ca.id) AS 'count_of_cards' 
FROM branches AS b 
JOIN employees AS e ON e.branch_id = b.id
JOIN employees_clients AS ec ON ec.employee_id = e.id
JOIN bank_accounts AS ba ON ba.client_id = ec.client_id
JOIN cards AS ca ON ca.bank_account_id = ba.id
GROUP BY b.`name`
ORDER BY count_of_cards DESC, b.`name`;


#10. Extract card's count
DELIMITER $$
CREATE FUNCTION udf_client_cards_count(`name` VARCHAR(30)) 
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(SELECT count(ca.id) AS cards
FROM clients AS cl
JOIN bank_accounts AS ba ON ba.client_id = cl.id
JOIN cards AS ca ON ca.bank_account_id = ba.id
WHERE cl.full_name = `name`
GROUP BY cl.full_name
);
END $$
DELIMITER ;

-- DROP FUNCTION udf_client_cards_count;
SELECT c.full_name, udf_client_cards_count('Baxy David') AS cards 
FROM clients AS c
WHERE c.full_name = 'Baxy David';

#11. Client Info
DELIMITER $$
CREATE PROCEDURE udp_clientinfo(full_name VARCHAR (50)) 
BEGIN
SELECT cl.full_name, cl.age, ba.account_number, 
concat('$', ba.balance) AS balance FROM clients AS cl
JOIN bank_accounts AS ba ON ba.client_id = cl.id
WHERE cl.full_name = full_name
GROUP BY cl.id;
END $$
DELIMITER ;

-- DROP PROCEDURE udp_clientinfo;
CALL udp_clientinfo('Hunter Wesgate');
