-- Lab --
USE camp;

#1. Mountains and Peaks
CREATE TABLE mountains (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45)
);

CREATE TABLE peaks (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45),
`mountain_id` INT,
CONSTRAINT `fk_peaks_mountains`
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`)
);

#2. Trip Organization
SELECT driver_id, v.vehicle_type, 
concat(c.first_name, ' ', c.last_name) AS 'driver_name'
FROM campers AS c
JOIN vehicles AS v ON v.driver_id = c.id;

#3. SoftUni Hiking
SELECT r.starting_point, r.end_point, r.leader_id,
concat(c.first_name, ' ', c.last_name)
FROM routes AS r
JOIN campers AS c ON r.leader_id =c.id;

#4. Delete Mountains
CREATE TABLE mountains (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45)
);

CREATE TABLE peaks (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(45),
`mountain_id` INT
);

ALTER TABLE `peaks`
ADD CONSTRAINT
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`)
ON DELETE CASCADE;
