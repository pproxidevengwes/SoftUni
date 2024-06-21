-- PT1 --
USE soft_uni;

#01. Find Names of All Employees by First Name
SELECT first_name, last_name FROM employees
WHERE lower(first_name) LIKE 'sa%'
ORDER BY employee_id;

#02. Find Names of All Employees by Last Name
SELECT first_name, last_name FROM employees
WHERE lower(last_name) LIKE '%ei%'
ORDER BY employee_id;

#03. Find First Names of All Employess
SELECT first_name FROM employees
WHERE department_id IN(3, 10) AND
year(hire_date) BETWEEN 1995 AND 2005
ORDER BY employee_id;

#04. Find All Employees Except Engineers
SELECT first_name, last_name FROM employees
WHERE job_title NOT LIKE '%engineer%'
ORDER BY employee_id;

#05. Find Towns with Name Length
SELECT name FROM towns
WHERE char_length(name) IN (5, 6)
ORDER BY name;

#06. Find Towns Starting With
SELECT * FROM towns
WHERE name REGEXP '^[MmKkBbEe]'
ORDER BY name;

#07. Find Towns Not Starting With
SELECT * FROM towns
WHERE upper(name) NOT LIKE 'R%'
AND upper(name) NOT LIKE 'B%'
AND upper(name) NOT LIKE 'D%'
ORDER BY name;

#08. Create View Employees Hired After
CREATE VIEW v_employees_hired_after_2000 AS 
SELECT first_name, last_name FROM employees
WHERE year(hire_date) > 2000;
SELECT * FROM v_employees_hired_after_2000;

#09. Length of Last Name
SELECT first_name, last_name FROM employees
WHERE char_length(last_name) = 5;
