#01. Select Employee Information
SELECT `id`, `first_name`, `last_name`, `job_title` FROM `employees`
ORDER BY id;

#02. Select Employees with Filter
SELECT `id`, concat_ws(' ', `first_name`, `last_name`) AS 'full_name' , `job_title`, `salary`
FROM `employees`
WHERE salary>1000
ORDER BY id ASC;

#03. Update Salary and Select
UPDATE employees 
SET salary = salary + 100
WHERE job_title = 'Manager';

SELECT salary FROM employees;

#04. Top Paid Employee
CREATE VIEW `v_top_paid` AS
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 1;
SELECT * FROM `v_top_paid`;

#05. Select Employees by Multiple Filters
SELECT * FROM employees
WHERE department_id = 4 AND salary >= 1000
ORDER BY id;

#06. Delete from Table
DELETE FROM employees
WHERE department_id IN (1,2);
SELECT * FROM employees;
