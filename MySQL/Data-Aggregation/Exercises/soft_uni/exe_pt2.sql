-- PT2 --
USE soft_uni;

#12. Employees Minimum Salaries
SELECT department_id, min(salary)
FROM employees
GROUP BY department_id
HAVING department_id IN(2, 5, 7);

#13. Employees Average Salaries
UPDATE employees SET salary = salary + 5000 
WHERE department_id = 1 AND salary > 30000 AND manager_id != 42;

SELECT department_id, avg(salary)
FROM employees
WHERE salary > 30000 AND manager_id != 42
GROUP BY department_id
ORDER BY department_id;

#14. Employees Maximum Salaries
SELECT department_id, max(salary) AS 'max_salary'
FROM employees
GROUP BY department_id
HAVING NOT `max_salary` BETWEEN 30000 AND 70000
ORDER BY department_id;

#15. Employees Count Salaries
SELECT count(salary) FROM employees
WHERE manager_id IS NULL;

#16. 3rd Highest Salary
SELECT department_id, 
(SELECT DISTINCT salary FROM employees AS e2
WHERE e1.department_id = e2.department_id
ORDER BY salary DESC
LIMIT 2,1) AS 'third_highest_salary'
FROM employees AS e1
GROUP BY department_id
HAVING third_highest_salary IS NOT NULL
ORDER BY department_id;

#17. Salary Challenge
SELECT e1.first_name, e1.last_name, e1.department_id
FROM employees AS e1
JOIN (
SELECT e2.department_id, avg(e2.salary) AS salary
FROM employees AS e2
GROUP BY e2.department_id
) AS `dep_avg` ON e1.department_id = dep_avg.department_id
WHERE e1.salary > dep_avg.salary
ORDER BY e1.department_id, e1.employee_id
LIMIT 10;

#18. Departments Total Salaries
SELECT department_id, sum(salary) AS 'total_salary'
FROM employees
GROUP BY department_id
ORDER BY department_id;
