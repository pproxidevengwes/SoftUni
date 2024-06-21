CREATE SCHEMA sgd;
USE sgd;
-- DROP SCHEMA sgd;

#01. Table Design
CREATE TABLE addresses (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (50) NOT NULL
);

CREATE TABLE offices (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`workspace_capacity` INT NOT NULL,
`website` VARCHAR (50) NULL,
`address_id` INT NOT NULL,
CONSTRAINT fk_offices_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id)
);

CREATE TABLE employees (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR (30) NOT NULL,
`last_name` VARCHAR (30) NOT NULL,
`age` INT NOT NULL,
`salary` DECIMAL(10,2) NOT NULL,
`job_title` VARCHAR (20) NOT NULL,
`happiness_level` CHAR NOT NULL
);

CREATE TABLE teams (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (40) NOT NULL,
`office_id` INT NOT NULL,
`leader_id` INT NOT NULL UNIQUE,
CONSTRAINT fk_teams_offices
FOREIGN KEY (office_id)
REFERENCES offices(id),
CONSTRAINT fk_teams_employees
FOREIGN KEY (leader_id)
REFERENCES employees(id)
);

CREATE TABLE games (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (50) NOT NULL UNIQUE,
`description` TEXT NULL,
`rating` FLOAT NOT NULL DEFAULT 5.5,
`budget` DECIMAL(10,2) NOT NULL,
`release_date` DATE NULL,
`team_id` INT NOT NULL,
CONSTRAINT fk_games_teams
FOREIGN KEY (team_id)
REFERENCES teams(id)
);

CREATE TABLE categories (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (10) NOT NULL
);

CREATE TABLE games_categories (
`game_id` INT NOT NULL,
`category_id` INT NOT NULL,
PRIMARY KEY (game_id, category_id),
CONSTRAINT fk_mapping_games
FOREIGN KEY (game_id)
REFERENCES games(id),
CONSTRAINT fk_mapping_categories
FOREIGN KEY (category_id)
REFERENCES categories(id)
);

#02. Insert
INSERT INTO games (`name`, rating, budget, team_id)
SELECT lower(reverse(substring(t.`name`,2))),
t.id, t.leader_id * 1000, t.id
FROM teams AS t
WHERE t.id BETWEEN 1 AND 9; 


#03. Update
UPDATE employees AS e SET salary = salary + 1000 
WHERE salary < 5000 AND age < 40 AND
(SELECT leader_id FROM teams
WHERE leader_id =e.id) >= 1;

#04. Delete
DELETE g FROM games AS g
LEFT JOIN games_categories AS gc ON g.id = gc.game_id
WHERE release_date IS NULL AND gc.category_id IS NULL;

#05. Employees
SELECT first_name, last_name, age, salary, happiness_level
FROM employees
ORDER BY salary, id;

#06. Addresses of the teams
SELECT t.`name` AS 'team_name', a.`name` AS 'address_name',
char_length(a.`name`) AS 'count_of_characters' 
FROM teams AS t
JOIN offices AS o ON t.office_id = o.id
JOIN addresses AS a ON o.address_id = a.id
WHERE o.website IS NOT NULL
ORDER BY t.`name`, a.`name`;

#07. Categories Info
SELECT c.`name`, count(g.id) AS 'games_count',
round(avg(g.budget),2) AS 'games_count',
max(rating) AS 'max_rating'
FROM categories AS c
JOIN games_categories AS gc ON gc.category_id =c.id
JOIN games AS g ON g.id = gc.game_id
GROUP BY c.`name`
HAVING max_rating >= 9.5
ORDER BY games_count DESC, c.`name`;

#08. Games of 2022
SELECT g.`name`,
g.release_date,
concat(substring(g.`description`,1,10), '...') AS 'summary',
(CASE
WHEN month(release_date) IN(1, 2, 3)
THEN 'Q1'
WHEN month(release_date) IN(4, 5, 6)
THEN 'Q2'
WHEN month(release_date) IN(7, 8, 9)
THEN 'Q3'
WHEN month(release_date) IN(10, 11, 12)
THEN 'Q4'
END ) AS 'quarter',
t.`name` AS 'team_name'
FROM games AS g
JOIN teams AS t ON t.id = g.team_id
WHERE month(g.release_date) % 2 = 0 AND
year(release_date) = 2022 AND
g.`name` LIKE '%2'
ORDER BY `quarter`;

#09. Full info for games
SELECT g.`name`,
(CASE
WHEN (g.budget < 50000) THEN 'Normal budget'
WHEN (g.budget > 50000) THEN 'Insufficient budget'
END) AS 'budget_level',
t.`name` AS 'team_name', a.`name` AS 'address_name'
FROM games AS g
LEFT JOIN teams AS t ON t.id = g.team_id
LEFT JOIN offices AS o ON t.office_id = o.id
LEFT JOIN addresses AS a ON o.address_id = a.id
LEFT JOIN games_categories AS gc ON g.id = gc.game_id
WHERE release_date IS NULL AND gc.category_id IS NULL
ORDER BY g.`name`;


#10. Find all basic information for a game
DELIMITER $$
CREATE FUNCTION udf_game_info_by_name (game_name VARCHAR (20))
RETURNS VARCHAR (200)
DETERMINISTIC
BEGIN
RETURN(
SELECT 
concat('The ', game_name, ' is developed by a ', t.`name`,
' in an office with an address ', a.`name`) FROM games AS g
JOIN teams AS t ON t.id = g.team_id
JOIN offices AS o ON t.office_id = o.id
JOIN addresses AS a ON o.address_id = a.id
WHERE g.`name` = game_name
);
END $$
DELIMITER ;

 DROP FUNCTION udf_game_info_by_name;

SELECT udf_game_info_by_name('Bitwolf') AS info;
SELECT udf_game_info_by_name('Fix San') AS info;
SELECT udf_game_info_by_name('Job') AS info;

#11. Update Budget of the Games
DELIMITER $$
CREATE PROCEDURE udp_update_budget(min_game_rating FLOAT)
BEGIN
UPDATE games AS g
SET g.budget = g.budget + 100000,
g.release_date = date_add(g.release_date, INTERVAL 1 year)
WHERE g.id NOT IN(SELECT game_id FROM games_categories)
AND g.rating > min_game_rating 
AND g.release_date IS NOT NULL;
END $$
DELIMITER ;

 DROP PROCEDURE udp_update_budget;

CALL udp_update_budget (8);
