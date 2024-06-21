CREATE SCHEMA colonial_journey_management_system_db;
USE colonial_journey_management_system_db;
DROP SCHEMA colonial_journey_management_system_db;
SET SQL_SAFE_UPDATES = 0;

#00. Table Design
CREATE TABLE planets (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE spaceports (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`planet_id` INT,
CONSTRAINT fk_spaceports_planets
FOREIGN KEY (planet_id)
REFERENCES planets(id)
);

CREATE TABLE spaceships (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`manufacturer` VARCHAR(30) NOT NULL,
`light_speed_rate` INT DEFAULT 0
);

CREATE TABLE journeys (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`journey_start` DATETIME NOT NULL,
`journey_end` DATETIME NOT NULL,
`purpose` ENUM
('Medical', 'Technical', 'Educational', 'Military'),
`destination_spaceport_id` INT,
`spaceship_id` INT,
CONSTRAINT fk_journeys_spaceports
FOREIGN KEY (destination_spaceport_id)
REFERENCES spaceports(id),
CONSTRAINT fk_journeys_spaceships
FOREIGN KEY (spaceship_id)
REFERENCES spaceships(id)
);

CREATE TABLE colonists (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`ucn` CHAR(10) NOT NULL UNIQUE,
`birth_date` DATE NOT NULL
);

CREATE TABLE travel_cards (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`card_number` CHAR(10) NOT NULL UNIQUE,
`job_during_journey` ENUM
('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook'),
`colonist_id` INT,
`journey_id` INT,
CONSTRAINT fk_travel_cards_colonists
FOREIGN KEY (colonist_id)
REFERENCES colonists(id),
CONSTRAINT fk_travel_cards_journeys
FOREIGN KEY (journey_id)
REFERENCES journeys(id)
);

#01. Insert
INSERT INTO travel_cards(card_number, 
job_during_journey, colonist_id, journey_id)
SELECT (IF (birth_date > '1980-01-01', 
concat(year(birth_date), day(birth_date), left(ucn, 4)),  
concat(year(birth_date), month(birth_date), right(ucn, 4)))),
(CASE 
WHEN c.id % 2 = 0 THEN 'Pilot'
WHEN c.id % 3 = 0 THEN 'Cook'
ELSE 'Engeneer'
END
), c.id,
left(ucn, 1)
FROM colonists AS c
WHERE c.id BETWEEN 96 AND 100;

#02. Update
UPDATE journeys SET purpose = (
CASE
WHEN id % 2 = 0 THEN 'Medical'
WHEN id % 3 = 0 THEN 'Technical'
WHEN id % 5 = 0 THEN 'Educational'
ELSE 'Military'
END
);

#03. Delete
DELETE FROM colonists AS c WHERE c.id NOT IN (
SELECT colonist_id FROM travel_cards);

#04. Extract all travel cards
SELECT card_number,	job_during_journey
FROM travel_cards
ORDER BY card_number;

#05. Extract all colonists
SELECT id, concat_ws(' ', `first_name`, `last_name`) 
AS 'full_name', ucn FROM colonists
ORDER BY first_name, last_name, id;

#06. Extract all military journeys
SELECT id, journey_start, journey_end FROM journeys
WHERE purpose LIKE 'Military'
ORDER BY journey_start;

#07. Extract all pilots
SELECT c.id, 
concat_ws(' ', `first_name`, `last_name`)
AS 'full_name' FROM colonists AS c
JOIN travel_cards AS tc ON c.id = tc.colonist_id
WHERE job_during_journey LIKE 'Pilot'
ORDER BY id;

#08. Count all colonists
SELECT count(*) FROM colonists AS c
LEFT JOIN travel_cards tc ON c.id = tc.colonist_id
LEFT JOIN journeys AS j ON j.id = tc.journey_id
WHERE j.purpose = 'Technical';

#09.Extract the fastest spaceship
SELECT ss.`name` AS 'spaceship_name', 
sp.`name` AS 'spaceport_name' FROM spaceships AS ss
JOIN journeys AS j ON ss.id = j.spaceship_id
JOIN spaceports AS sp  
ON sp.id = j.destination_spaceport_id
ORDER BY light_speed_rate DESC
LIMIT 1;

#10. Extract - pilots younger than 30 years
SELECT `name`, manufacturer FROM spaceships AS ss
JOIN journeys AS j ON ss.id = j.spaceship_id
LEFT JOIN travel_cards tc ON j.id = tc.journey_id
LEFT JOIN colonists c ON c.id = tc.colonist_id
WHERE year(c.birth_date) > 
year(date_sub('2019-01-01', INTERVAL 30 YEAR)) AND
tc.job_during_journey LIKE 'Pilot'
ORDER BY ss.`name`;

#11. Extract all educational mission
SELECT p.`name` AS 'planet_name', 
sp.`name` AS 'spaceport_name' FROM planets AS p
JOIN spaceports AS sp ON sp.planet_id = p.id
JOIN journeys AS j 
ON sp.id = j.destination_spaceport_id
WHERE j.purpose LIKE 'Educational'
ORDER BY spaceport_name DESC;

#12. Extract all planets and their journey count
SELECT p.`name` AS 'planet_name', 
count(j.id) AS 'journeys_count' FROM planets AS p
JOIN spaceports AS sp ON sp.planet_id = p.id
JOIN journeys AS j 
ON sp.id = j.destination_spaceport_id
GROUP BY P.`name`
ORDER BY journeys_count DESC, planet_name;

#13. Extract the shortest journey
SELECT j.id, p.`name`, sp.`name`, 
j.purpose AS 'job_name' FROM journeys AS j
JOIN spaceports AS sp 
ON j.destination_spaceport_id = sp.id
JOIN planets AS p ON sp.planet_id = p.id
ORDER BY datediff(j.journey_end, j.journey_start)
LIMIT 1;

#14. Extract the less popular job
SELECT tc.job_during_journey FROM travel_cards AS tc
WHERE tc.journey_id = (
SELECT j.id FROM journeys AS j
ORDER BY datediff(j.journey_end, j.journey_start) DESC
LIMIT 1)
GROUP BY tc.job_during_journey
ORDER BY count(tc.job_during_journey)
LIMIT 1;

#15. Get colonists count
DELIMITER $$
CREATE FUNCTION udf_count_colonists_by_destination_planet
(planet_name VARCHAR (30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN(SELECT count(*) FROM colonists AS c
JOIN travel_cards tc ON c.id = tc.colonist_id
JOIN journeys AS j ON j.id = tc.journey_id
JOIN spaceports AS sp  
ON sp.id = j.destination_spaceport_id
JOIN planets AS p ON p.id = sp.planet_id
WHERE p.`name` = planet_name
);
END $$
DELIMITER ;

SELECT p.name, udf_count_colonists_by_destination_planet
('Otroyphus') AS count FROM planets AS p
WHERE p.name = 'Otroyphus';

#16. Modify spaceship
DELIMITER $$
CREATE PROCEDURE udp_modify_spaceship_light_speed_rate
(spaceship_name VARCHAR(50), 
light_speed_rate_increse INT(11))                                                             
BEGIN
START TRANSACTION;
IF((SELECT ss.`name` FROM spaceships AS ss
WHERE ss.`name` = spaceship_name) IS NOT NULL)
THEN UPDATE spaceships AS ss
SET light_speed_rate = light_speed_rate + light_speed_rate_increse
WHERE `name` =  spaceship_name;
ELSE 
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Spaceship you are trying to modify does not exists.';
ROLLBACK;
END IF;
END $$
DELIMITER ;

DROP PROCEDURE udp_modify_spaceship_light_speed_rate;
CALL udp_modify_spaceship_light_speed_rate 
('Na Pesho koraba', 1914);
SELECT name, light_speed_rate FROM spacheships
WHERE name = 'Na Pesho koraba';

CALL udp_modify_spaceship_light_speed_rate ('USS Templar', 5);
SELECT name, light_speed_rate FROM spaceships 
WHERE name = 'USS Templar'
