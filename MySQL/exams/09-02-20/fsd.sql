CREATE DATABASE `fsd`;

USE fsd;

#01. Table Design
CREATE TABLE countries (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE towns (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`country_id` INT,
CONSTRAINT fk_towns_countries
FOREIGN KEY (country_id)
REFERENCES countries(id)
);

CREATE TABLE stadiums (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`capacity` INT NOT NULL,
`town_id` INT,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE teams (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`established` DATE NOT NULL,
`fan_base` BIGINT(20) NOT NULL DEFAULT 0,
`stadium_id` INT,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (stadium_id)
REFERENCES stadiums(id)
);

CREATE TABLE skills_data (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`dribbling` INT DEFAULT 0,
`pace` INT DEFAULT 0,
`passing` INT DEFAULT 0,
`shooting` INT DEFAULT 0,
`speed` INT DEFAULT 0,
`strength` INT DEFAULT 0
);

CREATE TABLE coaches(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`coach_level` INT NOT NULL DEFAULT 0
);

CREATE TABLE players(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`age` INT NOT NULL DEFAULT 0,
`position` CHAR(1) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`hire_date` DATETIME,
`skills_data_id` INT,
`team_id` INT,
CONSTRAINT fk_players_skills_data
FOREIGN KEY (skills_data_id)
REFERENCES skills_data(id),
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams(id)
);

CREATE TABLE players_coaches(
`player_id` INT,
`coach_id` INT,
CONSTRAINT fk_pc_players
FOREIGN KEY (player_id)
REFERENCES players(id),
CONSTRAINT fk_pc_coaches
FOREIGN KEY (coach_id)
REFERENCES coaches(id)
);


#02. Insert
INSERT INTO coaches(first_name, last_name, salary, coach_level)
SELECT first_name, last_name, 2 * salary, char_length(first_name)
FROM players 
WHERE age >= 45;


#03. Update
UPDATE coaches AS c
SET c.coach_level = c.coach_level + 1
WHERE c.first_name LIKE 'A%' AND
(SELECT count(player_id) FROM players_coaches
WHERE coach_id = c.id) >= 1;


#04. Delete
DELETE FROM players WHERE age >= 45; 

#05. Players
SELECT first_name, age, salary FROM players
ORDER BY salary DESC;

#06. Young offense players without contract
SELECT p.`id`, concat_ws(' ', `first_name`, `last_name`) AS 'full_name',
`age`, `position`, `hire_date` FROM players AS p
JOIN skills_data AS sk ON sk.id = p.skills_data_id
WHERE `age` < 23 AND `position` = 'A' AND `hire_date` IS NULL 
AND sk.`strength` > 50
ORDER BY salary;



#07. Detail info for all teams
SELECT t.`name` AS 'team_name',
t.established, 
t.fan_base, 
(SELECT count(id) FROM players WHERE team_id = t.id) AS 'players_count'
FROM teams AS t
ORDER BY players_count DESC, fan_base DESC;


#08. The fastest player by towns
SELECT max(sd.speed) AS 'max_speed', t.`name` AS 'town_name' 
FROM players AS p
LEFT JOIN skills_data AS sd ON sd.id = p.skills_data_id
RIGHT JOIN teams AS tm ON tm.id = p.team_id
RIGHT JOIN stadiums AS s ON s.id = tm.stadium_id
RIGHT JOIN towns AS t ON t.id = s.town_id
WHERE tm.name != 'Devify'
GROUP BY t.`name`
ORDER BY max_speed DESC, town_name;


#09. Total salaries and players by country
SELECT c.`name`,
count(p.id) AS 'total_count_of_players',
sum(p.salary) AS 'total_sum_of_salaries'
FROM countries AS c
LEFT JOIN towns AS t ON t.country_id = c.id
LEFT JOIN stadiums AS s ON s.town_id = t.id
LEFT JOIN teams AS tm ON tm.stadium_id = s.id
LEFT JOIN players AS p ON p.team_id = tm.id
GROUP BY c.`name`
ORDER BY total_count_of_players DESC, c.`name`;
    


#10. Find all players that play on stadium
DELIMITER $$
CREATE FUNCTION udf_stadium_players_count(stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(
SELECT count(*) FROM players p
JOIN stadiums AS s ON s.town_id = teams.id
JOIN teams AS tm ON tm.stadium_id = s.id
WHERE s.`name` = stadium_name
);
END $$
DELIMITER ;

DROP FUNCTION udf_stadium_players_count;
SELECT udf_stadium_players_count ('Linklinks') as `count`;

#11. Find good playmaker by teams
DELIMITER $$
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT,
team_name VARCHAR(45))
BEGIN
SELECT concat_ws(' ', p.first_name, p.last_name) AS full_name,
p.age,
p.salary,
sd.dribbling,
sd.speed,
tm.name AS team_name
FROM players AS p 
JOIN skills_data AS sd ON sd.id = p.skills_data_id
JOIN teams AS tm ON tm.id = p.team_id
WHERE sd.dribbling > min_dribble_points AND tm.name = team_name
GROUP BY p.id
ORDER BY avg(sd.speed) DESC
LIMIT 1;
END $$
DELIMITER ;

DROP PROCEDURE udp_find_playmaker;
CALL udp_find_playmaker (20, 'Skyble');
