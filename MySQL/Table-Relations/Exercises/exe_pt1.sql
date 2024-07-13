-- PT1 --
CREATE DATABASE table_relations;
USE table_relations;

#01. One-To-One Relationship
CREATE TABLE people (
`person_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`first_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`passport_id` INT NOT NULL UNIQUE
);

CREATE TABLE passports (
`passport_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`passport_number` VARCHAR(8) NOT NULL UNIQUE
) AUTO_INCREMENT = 101;

INSERT INTO people (`first_name`, `salary` , `passport_id`) VALUE 
('Roberto', 43300, 102),
('Tom', 56100, 103),
('Yana', 60200, 101);

INSERT INTO passports (`passport_number`) VALUE 
('N34FG21B'),
('K65LO4R7'),
('ZE657QP2');

ALTER TABLE people
ADD CONSTRAINT pk_people
PRIMARY KEY (`person_id`),
ADD CONSTRAINT fk_people_passports
FOREIGN KEY (`passport_id`)
REFERENCES passports (`passport_id`);

--------------------------------------------
#02. One-To-Many Relationship
CREATE TABLE manufacturers (
`manufacturer_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(20) NOT NULL,
`established_on` DATE NOT NULL 
);

CREATE TABLE models (
`model_id` INT AUTO_INCREMENT UNIQUE NOT NULL,
`name` VARCHAR(20) NOT NULL,
`manufacturer_id` INT NOT NULL 
) AUTO_INCREMENT = 101;

INSERT INTO manufacturers (`name`, `established_on`) VALUES
('BMW', '1916-03-01'),
('Tesla', '2003-01-01'),
('Lada', '1966-05-01');

INSERT INTO models (`name`, `manufacturer_id`) VALUES
('X1', 1),
('i6', 1),
('Model S', 2),
('Model X', 2),
('Model 3', 2),
('Nova', 3);

ALTER TABLE manufacturers 
ADD CONSTRAINT pk_manufacturer
PRIMARY KEY (manufacturer_id);

ALTER TABLE models
ADD CONSTRAINT pk_model
PRIMARY KEY (model_id),
ADD CONSTRAINT fk_models_manufacturers
FOREIGN KEY (manufacturer_id)
REFERENCES  manufacturers(manufacturer_id);

--------------------------------------------
#03. Many-To-Many Relationship
CREATE TABLE exams ( 
`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
) AUTO_INCREMENT = 101;

CREATE TABLE students (
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE students_exams (
`student_id` INT NOT NULL,
`exam_id` INT NOT NULL,
CONSTRAINT fk_students_exams
FOREIGN KEY (`student_id`)
REFERENCES students(`student_id`),
CONSTRAINT fk_exams_students
FOREIGN KEY (`exam_id`)
REFERENCES exams(`exam_id`)
);

INSERT INTO exams (`name`) VALUE 
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

INSERT INTO students (`name`) VALUE 
('Mila'),
('Toni'),
('Ron');

INSERT INTO students_exams (`student_id`, `exam_id`) VALUE 
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);


#04. Self-Referencing
CREATE TABLE teachers(
`teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (20) NOT NULL,
`manager_id` INT DEFAULT NULL
) AUTO_INCREMENT = 101;

INSERT INTO teachers (`name`, `manager_id`) VALUES
('John', NULL),
('Maya', 106),
('Silvia', 106),
('Ted', 105),
('Mark', 101),
('Greta', 101);

ALTER TABLE teachers
ADD CONSTRAINT fk_teacher_manager
FOREIGN KEY (manager_id)
REFERENCES teachers(teacher_id);
