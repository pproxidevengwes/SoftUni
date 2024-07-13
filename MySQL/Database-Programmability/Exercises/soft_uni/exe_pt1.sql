-- PT1 --
USE soft_uni;

#01. Employees with Salary Above 35000
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e
WHERE e.salary > 35000
ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above_35000();
DROP PROCEDURE usp_get_employees_salary_above_35000;

#02. Employees with Salary Above Number
DELIMITER $$
CREATE PROCEDURE usp_get_employees_salary_above(salary_limit DOUBLE(19,4)) 
BEGIN 
SELECT e.first_name, e.last_name FROM employees AS e
WHERE e.salary >= salary_limit
ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_salary_above(45000);
DROP PROCEDURE usp_get_employees_salary_above;

#03. Town Names Starting With
DELIMITER $$
CREATE PROCEDURE usp_get_towns_starting_with(town_name_start TEXT)
BEGIN
SELECT t.name FROM towns AS t 
WHERE t.name LIKE concat(town_name_start, '%')
ORDER BY t.name;
END $$
DELIMITER ;

CALL usp_get_towns_starting_with('b');
DROP PROCEDURE usp_get_towns_starting_with;

#04. Employees from Town
DELIMITER $$
CREATE PROCEDURE usp_get_employees_from_town(town_name TEXT)
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e 
JOIN addresses AS a ON e.address_id = a.address_id
JOIN towns AS t ON t.town_id = a.town_id
WHERE t.name = town_name
ORDER BY e.first_name, e.last_name, e.employee_id;
END $$
DELIMITER ;

CALL usp_get_employees_from_town('Sofia');
DROP PROCEDURE usp_get_employees_from_town;

#05. Salary Level Function
DELIMITER $$
CREATE FUNCTION ufn_get_salary_level(salary DOUBLE(19,4))
RETURNS VARCHAR(7) DETERMINISTIC
RETURN (CASE
WHEN salary < 30000 THEN 'Low'
WHEN salary <= 50000 THEN 'Average'
ELSE 'High'
END);
$$
DELIMITER ;

SELECT (ufn_get_salary_level(13500.00)); 
SELECT (ufn_get_salary_level(43300.00));    
SELECT (ufn_get_salary_level(125500.00));    

#06. Employees by Salary Level
DELIMITER $$
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN
SELECT e.first_name, e.last_name FROM employees AS e
WHERE ufn_get_salary_level(e.salary) = salary_level
ORDER BY e.first_name DESC, e.last_name DESC;
END $$
DELIMITER ;

CALL usp_get_employees_by_salary_level ('High');
DROP PROCEDURE usp_get_employees_by_salary_level ;

#07. Define Function
CREATE FUNCTION ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))  
RETURNS BIT
DETERMINISTIC
RETURN word REGEXP(concat('^[', set_of_letters, ']+$'));

SELECT ufn_is_word_comprised('oistmiahf', 'Sofia')  ;
