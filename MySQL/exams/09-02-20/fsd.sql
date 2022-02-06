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
SELECT t.`name` AS 'team_name', `established`, `fan_base`, 
(SELECT count(id) FROM players WHERE team_id = t.id) AS 'players_count'
FROM teams AS t
GROUP BY t.`name`
ORDER BY players_count DESC, fan_base DESC;



#08. The fastest player by towns


#09. Total salaries and players by country



#10. Find all players that play on stadium



#11. Find good playmaker by teams







