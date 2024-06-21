CREATE SCHEMA instd;
USE instd;
SET SQL_SAFE_UPDATES = 0;

#01. Table Design
CREATE TABLE users (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(30) NOT NULL,
`email` VARCHAR(50) NOT NULL,
`gender` CHAR NOT NULL,
`age` INT NOT NULL,
`job_title` VARCHAR(40) NOT NULL,
`ip` VARCHAR(30) NOT NULL
);

CREATE TABLE addresses (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`address` VARCHAR(30) NOT NULL,
`town` VARCHAR(30) NOT NULL,
`country` VARCHAR(30) NOT NULL,
`user_id` INT,
CONSTRAINT fk_addresses_users
FOREIGN KEY (user_id)
REFERENCES users(id)
);

CREATE TABLE photos (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`description` TEXT NOT NULL, 
`date` DATETIME NOT NULL,
`views` INT NOT NULL DEFAULT 0
);

CREATE TABLE users_photos (
`user_id` INT,
`photo_id` INT,
CONSTRAINT fk_mapping_users
FOREIGN KEY (user_id)
REFERENCES users(id),
CONSTRAINT fk_mappng_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE likes (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`photo_id` INT,
`user_id` INT,
CONSTRAINT fk_likes_users
FOREIGN KEY (user_id)
REFERENCES users(id),
CONSTRAINT fk_likes_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

CREATE TABLE comments (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`comment` VARCHAR(255) NOT NULL,
`date` DATETIME NOT NULL,
`photo_id` INT,
CONSTRAINT fk_comments_photos
FOREIGN KEY (photo_id)
REFERENCES photos(id)
);

#02. Insert
INSERT INTO addresses(address, town, country, user_id) 
SELECT u.username, u.`password`, u.ip, u.age FROM users AS u 
WHERE u.gender = 'M';

#03. Update
UPDATE addresses AS a SET country = (
CASE 
WHEN country LIKE 'B%' THEN 'Blocked'
WHEN country LIKE 'T%' THEN 'Test'
WHEN country LIKE 'P%' THEN 'In Progress'
ELSE country
END
);

#04. Delete 
DELETE FROM addresses AS a WHERE a.id % 3 = 0;

#05. Users
SELECT u.username, u.gender, u.age FROM users AS u 
ORDER BY age DESC, username;

#06. Extract 5 most commented photos
SELECT p.id, p.`date`, p.description, 
count(c.id) AS 'commentsCount' FROM photos AS p
JOIN comments AS c ON p.id = c.photo_id
GROUP BY p.id
ORDER BY commentsCount DESC, p.id
LIMIT 5;

#07. Lucky users
SELECT concat_ws(' ', u.id, u.username), u.email FROM users AS u
JOIN users_photos AS up ON u.id = up.user_id
AND u.id = up.photo_id
ORDER BY u.id;

#08. Count likes and comments
SELECT p.id, count(DISTINCT l.id) AS 'likes_count',
count(DISTINCT c.id) AS 'comments_count' FROM photos AS p
LEFT JOIN likes AS l ON p.id = l.photo_id
LEFT JOIN comments AS c ON p.id = c.photo_id
GROUP BY p.id
ORDER BY likes_count DESC, comments_count DESC, p.id DESC;

#09. The photo on the tenth day of the month
SELECT concat(left(p.`description`, 30), '...') AS 'summary',
p.`date` FROM photos AS p
WHERE DAY(p.`date`) = 10
GROUP BY p.id, p.`date`
ORDER BY p.`date` DESC;

#10. Get user’s photos count
DELIMITER $$
CREATE FUNCTION udf_users_photos_count(username VARCHAR (30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT count(*) FROM users AS u
JOIN users_photos AS up ON u.id = up.user_id
WHERE u.username = username);
END $$
DELIMITER ;

SELECT udf_users_photos_count('ssantryd') AS photosCount;

#11. Increase user age
DELIMITER $$
CREATE PROCEDURE udp_modify_user(address VARCHAR(30), town VARCHAR(30))
BEGIN
IF((SELECT a.address FROM addresses AS a 
WHERE address = a.address) IS NOT NULL)
THEN UPDATE users AS u 
JOIN addresses AS aa ON u.id = aa.user_id
SET u.age = u.age + 10 WHERE aa.address = address AND aa.town = town;
END IF;
END $$
DELIMITER ;

CALL udp_modify_user ('97 Valley Edge Parkway', 'Divinópolis');
SELECT u.username, u.email,u.gender,u.age,u.job_title FROM users AS u
WHERE u.username = 'eblagden21';
