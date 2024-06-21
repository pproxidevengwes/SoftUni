-- Lab --
USE soft_uni;

#1. Count Employees by Town
DELIMITER ##
CREATE FUNCTION ufn_count_employees_by_town(townname VARCHAR(100))
RETURNS INT
DETERMINISTIC
BEGIN
DECLARE x INT;
SET x := (SELECT count(*)
FROM towns AS t
LEFT JOIN addresses AS a ON t.town_id = a.town_id
LEFT JOIN employees AS e ON e.address_id = a.address_id
WHERE t.name = townname);
RETURN x;
END ##

#2. Employees Promotion
DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(100))
BEGIN
UPDATE employees AS e
RIGHT JOIN departments AS d ON e.department_id = d.department_id
SET salary = salary * 1.05 
WHERE d.name = department_name;
END $$

#3. Employees Promotion By ID
DELIMITER &&
CREATE PROCEDURE usp_raise_salary_by_id(id INT) 
BEGIN
START TRANSACTION;
IF((SELECT count(*) FROM employees WHERE employee_id = id) > 0)
THEN
UPDATE employees 
SET salary = salary * 1.05 
WHERE employee_id = id;
COMMIT;
ELSE ROLLBACK;
END IF;
END &&

CALL usp_raise_salary_by_id(11111);

#4. Triggered
CREATE TABLE deleted_employees (
`employee_id` INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(50) NOT NULL,
`last_name`VARCHAR(50) NOT NULL,
`middle_name`VARCHAR(50) NOT NULL,
`job_title` VARCHAR(50) NOT NULL,
`department_ID` INT(10) NOT NULL,
`salary` DECIMAL(19, 4) NOT NULL
);

DELIMITER $$
CREATE TRIGGER tr_deleted_employees
AFTER DELETE ON employees FOR EACH ROW
BEGIN
INSERT INTO deleted_employees (first_name, last_name, 
middle_name, job_title, department_id, salary)
VALUES (OLD.first_name, OLD.last_name, OLD.middle_name, 
OLD.job_title, OLD.department_id, OLD.salary);
END; $$

DELIMITER ;
DROP TRIGGER tr_deleted_employees;
