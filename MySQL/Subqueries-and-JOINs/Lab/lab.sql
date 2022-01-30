-- Lab --
#1. Managers
SELECT e.`employee_id`, concat(e.`first_name`, ' ', e.`last_name`) AS 'full_name', 
d.`department_id`, d.`name` AS 'department_name'
FROM `employees` AS e
JOIN `departments` AS d
ON d.`manager_id` = e.`employee_id`
ORDER BY `employee_id`
LIMIT 5;

#2. Towns and Addresses



#3. Employees Without Managers


#4. High Salary

