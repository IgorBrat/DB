-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boklach
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `boklach` ;

-- -----------------------------------------------------
-- Schema boklach
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boklach` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `boklach` ;

-- -----------------------------------------------------
-- TABLES CREATION
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `boklach`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`region` ;

CREATE TABLE IF NOT EXISTS `boklach`.`region` (
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`))
;


-- -----------------------------------------------------
-- Table `boklach`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`city` ;

CREATE TABLE IF NOT EXISTS `boklach`.`city` (
  `name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`, `region_name`),
  CONSTRAINT `city_region`
    FOREIGN KEY (`region_name`)
    REFERENCES `boklach`.`region` (`name`))
;


-- -----------------------------------------------------
-- Table `boklach`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`user` ;

CREATE TABLE IF NOT EXISTS `boklach`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `phone` VARCHAR(12) NOT NULL UNIQUE,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`))

AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`agency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`agency` ;

CREATE TABLE IF NOT EXISTS `boklach`.`agency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL UNIQUE,
  `name` VARCHAR(50) NOT NULL,
  `owner` VARCHAR(50) NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(50) NOT NULL,
  `hq_address` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `agency_city_region`
    FOREIGN KEY (`city_name` , `region_name`)
    REFERENCES `boklach`.`city` (`name` , `region_name`),
  CONSTRAINT `agency_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `boklach`.`user` (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`animator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`animator` ;

CREATE TABLE IF NOT EXISTS `boklach`.`animator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL UNIQUE,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(50) NOT NULL,
  `salary_per_hour` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `animator_city_region`
    FOREIGN KEY (`city_name` , `region_name`)
    REFERENCES `boklach`.`city` (`name` , `region_name`),
  CONSTRAINT `animator_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `boklach`.`user` (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`agency_animator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`agency_animator` ;

CREATE TABLE IF NOT EXISTS `boklach`.`agency_animator` (
  `agency_id` INT NOT NULL,
  `animator_id` INT NOT NULL,
  PRIMARY KEY (`animator_id`, `agency_id`),
  CONSTRAINT `animator_agency_agency`
    FOREIGN KEY (`agency_id`)
    REFERENCES `boklach`.`agency` (`id`),
  CONSTRAINT `animator_agency_animator`
    FOREIGN KEY (`animator_id`)
    REFERENCES `boklach`.`animator` (`id`))
;


-- -----------------------------------------------------
-- Table `boklach`.`client_card`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`client_card` ;

CREATE TABLE IF NOT EXISTS `boklach`.`client_card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`event` ;

CREATE TABLE IF NOT EXISTS `boklach`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`card_event_discount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`card_event_discount` ;

CREATE TABLE IF NOT EXISTS `boklach`.`card_event_discount` (
  `client_card_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `discount_percentage` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`client_card_id`, `event_id`),
  CONSTRAINT `card_event_discount_client_card`
    FOREIGN KEY (`client_card_id`)
    REFERENCES `boklach`.`client_card` (`id`),
  CONSTRAINT `card_event_discount_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
;


-- -----------------------------------------------------
-- Table `boklach`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`client` ;

CREATE TABLE IF NOT EXISTS `boklach`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL UNIQUE,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(50) NOT NULL,
  `street_address` VARCHAR(50) NULL DEFAULT NULL,
  `client_card_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `client_city_region`
    FOREIGN KEY (`city_name` , `region_name`)
    REFERENCES `boklach`.`city` (`name` , `region_name`),
  CONSTRAINT `client_client_card`
    FOREIGN KEY (`client_card_id`)
    REFERENCES `boklach`.`client_card` (`id`),
  CONSTRAINT `client_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `boklach`.`user` (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`equipment` ;

CREATE TABLE IF NOT EXISTS `boklach`.`equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`event_equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`event_equipment` ;

CREATE TABLE IF NOT EXISTS `boklach`.`event_equipment` (
  `event_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`event_id`, `equipment_id`),
  CONSTRAINT `event_equipment_equipment`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `boklach`.`equipment` (`id`),
  CONSTRAINT `event_equipment_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
;


-- -----------------------------------------------------
-- Table `boklach`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`order` ;

CREATE TABLE IF NOT EXISTS `boklach`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `duration` TIME NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(50) NOT NULL,
  `street_address` VARCHAR(50) NOT NULL,
  `total_price` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_city_region`
    FOREIGN KEY (`city_name` , `region_name`)
    REFERENCES `boklach`.`city` (`name` , `region_name`),
  CONSTRAINT `order_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `boklach`.`client` (`id`),
  CONSTRAINT `order_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`order_agency_animator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`order_agency_animator` ;

CREATE TABLE IF NOT EXISTS `boklach`.`order_agency_animator` (
  `order_id` INT NOT NULL,
  `agency_id` INT NOT NULL,
  `animator_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `agency_id`, `animator_id`),
  CONSTRAINT `agency_animator`
    FOREIGN KEY (`agency_id` , `animator_id`)
    REFERENCES `boklach`.`agency_animator` (`agency_id` , `animator_id`),
  CONSTRAINT `order_agency_animator_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `boklach`.`order` (`id`))
;

-- -----------------------------------------------------
-- INSERT STATEMENTS
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Table `boklach`.`region`
-- -----------------------------------------------------
INSERT INTO region (name) VALUES ('Vinnitska');
INSERT INTO region (name) VALUES ('Chernivetska');
INSERT INTO region (name) VALUES ('Lvivska');
INSERT INTO region (name) VALUES ('Kyivska');
INSERT INTO region (name) VALUES ('Ternopilska');
INSERT INTO region (name) VALUES ('Kharkivska');
INSERT INTO region (name) VALUES ('Odeska');
INSERT INTO region (name) VALUES ('Volynska');
INSERT INTO region (name) VALUES ('Poltavska');
INSERT INTO region (name) VALUES ('Mykolaivska');
-- -----------------------------------------------------
-- Table `boklach`.`city`
-- -----------------------------------------------------
INSERT INTO city (name, region_name) VALUES ('Chernivtsi','Vinnitska');
INSERT INTO city (name, region_name) VALUES ('Chernivtsi','Chernivetska');
INSERT INTO city (name, region_name) VALUES ('Lviv','Lvivska');
INSERT INTO city (name, region_name) VALUES ('Kyiv','Kyivska');
INSERT INTO city (name, region_name) VALUES ('Kharkiv','Kharkivska');
INSERT INTO city (name, region_name) VALUES ('Poltava','Poltavska');
INSERT INTO city (name, region_name) VALUES ('Odesa','Odeska');
INSERT INTO city (name, region_name) VALUES ('Ternopil','Ternopilska');
INSERT INTO city (name, region_name) VALUES ('Lutsk','Volynska');
INSERT INTO city (name, region_name) VALUES ('Vinnitsya','Vinnitska');
-- -----------------------------------------------------
-- Table `boklach`.`user`
-- -----------------------------------------------------
INSERT INTO user (email, phone) VALUES ('zoryaver@mail.ua', '0504545455');
INSERT INTO user (email, phone) VALUES ('zenykbo@ukr.net', '0667857111');
INSERT INTO user (email, phone) VALUES ('olechkar@ukr.net', '0505006060');
INSERT INTO user (email, phone) VALUES ('ppother@gmail.com', '0345006060');
INSERT INTO user (email, phone) VALUES ('pp@gmail.com', '0341000111');
INSERT INTO user (email, phone) VALUES ('pepppr@gmail.com', '0667777474');
INSERT INTO user (email, phone) VALUES ('jm@mail.ua', '0953003003');
INSERT INTO user (email, phone) VALUES ('jackym@gmail.com', '0561100011');
INSERT INTO user (email, phone) VALUES ('petya@ukr.net', '0507676767');
INSERT INTO user (email, phone) VALUES ('mchivan@gmail.com', '0955004132');
INSERT INTO user (phone, email) VALUES ('0971231231', 'ldb@ukr.net');
INSERT INTO user (phone, email) VALUES ('0955555555', 'hkeeper@ukr.net');
INSERT INTO user (phone, email) VALUES ('0968759090', 'nonamef@mail.com');
INSERT INTO user (phone, email) VALUES ('0958759090', 'nepfirm@mail.com');
INSERT INTO user (phone, email) VALUES ('0959009090', 'get_fun@gmail.com');
INSERT INTO user (phone, email) VALUES ('0666677777', 'ghmail@mail.ua');
INSERT INTO user (phone, email) VALUES ('0505000550', 'bphfirm@urk.net');
INSERT INTO user (phone, email) VALUES ('0669669999', 'wedd@gmail.com');
INSERT INTO user (phone, email) VALUES ('0991231231', 'funnier@mail.ua');
INSERT INTO user (phone, email) VALUES ('0506070123', 'entertly@gmail.com');
INSERT INTO user (phone, email) VALUES ('0561010100', 'felibocj@ukr.net');
INSERT INTO user (phone, email) VALUES ('0669130300', 'barkov1998@mail.ua');
INSERT INTO user (phone, email) VALUES ('0991111111', 'bogberyl@gmail.com');
INSERT INTO user (phone, email) VALUES ('0909595958', 'samtari@ukr.net');
INSERT INTO user (phone, email) VALUES ('0500005050', 'simsem@urk.net');
INSERT INTO user (phone, email) VALUES ('0669009090', 'martgr@mail.ua');
INSERT INTO user (phone, email) VALUES ('0995050500', 'gavrylko@ukr.net');
INSERT INTO user (phone, email) VALUES ('0506006060', 'some@gmail.com');
INSERT INTO user (phone, email) VALUES ('0567007090', 'avmaria@mail.ua');
INSERT INTO user (phone, email) VALUES ('0555060080', 'ivalo@mail.ua');
-- -----------------------------------------------------
-- Table `boklach`.`agency`
-- -----------------------------------------------------
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (11, 'LDB', 'Ivanov I.I.', 'Kyiv','Kyivska', 'Holovna str. 3');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (12, 'HKeeper', 'Petrov P.P.', 'Poltava','Poltavska', 'Hreniv str. 4');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (13, 'NoNameFun', 'Ivanov I.I.', 'Odesa','Odeska', 'Somiv str. 10');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (14, 'NEP', 'Petrov P.P.', 'Chernivtsi', 'Vinnitska', 'Somiv str. 100');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (15, 'GetFun', 'Ivanov I.I.', 'Chernivtsi','Chernivetska', 'Komarova str. 30');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (16, 'GH', 'Petrov P.P.', 'Kharkiv','Kharkivska', 'Holovna str. 200');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (17, 'BPH', 'Petrov O.O.', 'Ternopil','Ternopilska', 'Nesalezhnosti blrd. 12');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (18, 'Wedd', 'Olegov O.O.', 'Lutsk','Volynska', 'Sone str. 40');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (19, 'Funnier', 'Petrov O.O.', 'Chernivtsi','Chernivetska', 'Shevchenka str. 55');
INSERT INTO agency (user_id, name, owner, city_name, region_name, hq_address) 
	VALUES (20, 'Enterly', 'Ivanov I.I.', 'Lviv','Lvivska', 'Great blrd. 1');
-- -----------------------------------------------------
-- Table `boklach`.`animator`
-- -----------------------------------------------------
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (1, 'Zoryan', 'Ivan', 'Chernivtsi','Vinnitska', 800);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (2, 'Zakharov', 'Zenovii', 'Poltava','Poltavska', 900);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (3, 'Karlo', 'Oleg', 'Ternopil','Ternopilska', 1100);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (4, 'Perta', 'Pinya', 'Odesa','Odeska', 700);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (5, 'Perta', 'Pedro', 'Lviv','Lvivska', 600);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (6, 'Pepper', 'Petro', 'Kharkiv','Kharkivska', 650);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (7, 'Mal', 'John', 'Vinnitsya','Vinnitska', 1000);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (8, 'Mal', 'Jack', 'Kyiv','Kyivska', 800);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (9, 'Semkiv', 'Semen', 'Lutsk','Volynska', 900);
INSERT INTO animator (user_id, surname, name, city_name, region_name, salary_per_hour) 
	VALUES (10, 'Chiv', 'Taras', 'Chernivtsi','Chernivetska', 750);
-- -----------------------------------------------------
-- Table `boklach`.`agency_animator`
-- -----------------------------------------------------
INSERT INTO agency_animator (agency_id, animator_id) VALUES (1,1);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (1,3);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (1,4);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (2,2);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (2,10);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (3,5);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (3,6);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (3,7);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (3,8);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (4,9);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (5,1);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (5,2);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (5,5);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (6,7);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (6,10);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (7,1);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (7,4);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (8,3);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (8,6);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (8,8);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (8,9);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (9,6);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (9,7);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (10,1);
INSERT INTO agency_animator (agency_id, animator_id) VALUES (10,10);
-- -----------------------------------------------------
-- Table `boklach`.`client_card`
-- -----------------------------------------------------
INSERT INTO client_card (name) VALUES ("silver");
INSERT INTO client_card (name) VALUES ("silver plus");
INSERT INTO client_card (name) VALUES ("gold");
INSERT INTO client_card (name) VALUES ("gold plus");
INSERT INTO client_card (name) VALUES ("platinum");
-- -----------------------------------------------------
-- Table `boklach`.`event`
-- -----------------------------------------------------
INSERT INTO event (name) VALUES ("birthday party");
INSERT INTO event (name) VALUES ("wedding");
INSERT INTO event (name) VALUES ("disco");
INSERT INTO event (name) VALUES ("magician concert");
INSERT INTO event (name) VALUES ("first school day party");
-- -----------------------------------------------------
-- Table `boklach`.`card_event_discount`
-- -----------------------------------------------------
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (1,1,20);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (1,2,20);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (2,1,25);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (2,2,25);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (2,3,25);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (3,1,40);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (3,2,40);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (3,3,40);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (4,1,45);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (4,2,45);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (4,3,45);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (4,4,45);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (5,1,60);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (5,2,60);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (5,3,60);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (5,4,60);
INSERT INTO card_event_discount (client_card_id, event_id, discount_percentage) VALUES (5,5,60);
-- -----------------------------------------------------
-- Table `boklach`.`client`
-- -----------------------------------------------------
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (21, 'Bochko', 'Felicia', '1995-09-01', 'Kyiv','Kyivska', 'Holovna str. 1', 1);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (22, 'Barinov', 'Oleksii', '1995-06-06', 'Vinnitsya','Vinnitska', 'Chernivetska str. 5', 5);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (23, 'Beryl', 'Bogdan', '1975-09-01', 'Kharkiv','Kharkivska', 'Lvivska str. 10', NULL);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id)
	VALUES (24, 'Saman', 'Taras', '1970-01-05', 'Lutsk','Volynska', 'Nesalezhnosti blvd. 100', 3);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (25, 'Simov', 'Semen', '1990-03-10', 'Chernivtsi','Chernivetska', 'Nesalezhnosti blvd. 11', NULL);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (26, 'Gryniv', 'Marta', '1995-09-10', 'Lviv','Lvivska', 'Semka str. 15', 4);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (27, 'Gavra', 'Petro', NULL, 'Ternopil','Ternopilska', 'Sorov str. 20', 2);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (28, 'Avera', 'Ivan', '1960-09-09', 'Odesa','Odeska', 'Ukrainska str. 200', 1);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (29, 'Avera', 'Maria', NULL, 'Kharkiv','Kharkivska', 'Sorov str. 13', 3);
INSERT INTO client (user_id, surname, name, birthday, city_name, region_name, street_address, client_card_id) 
	VALUES (30, 'Losa', 'Ivan', '2000-07-05', 'Chernivtsi','Vinnitska', 'Holovna str. 50', NULL);
-- -----------------------------------------------------
-- Table `boklach`.`equipment`
-- -----------------------------------------------------
INSERT INTO equipment (name) VALUES ('micro');
INSERT INTO equipment (name) VALUES ('earphone');
INSERT INTO equipment (name) VALUES ('magic stick');
INSERT INTO equipment (name) VALUES ('balloon');
INSERT INTO equipment (name) VALUES ('carnaval mask');
INSERT INTO equipment (name) VALUES ('dj set');
INSERT INTO equipment (name) VALUES ('speaker');
INSERT INTO equipment (name) VALUES ('magic hat');
INSERT INTO equipment (name) VALUES ('synthesizer');
INSERT INTO equipment (name) VALUES ('cosplay costume');
-- -----------------------------------------------------
-- Table `boklach`.`event_equipment`
-- -----------------------------------------------------
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (1,4,20);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (1,5,10);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (1,7,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (1,9,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (1,10,4);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (2,1,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (2,2,4);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (2,4,20);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (2,7,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (3,1,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (3,2,2);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (3,6,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (4,1,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (4,2,2);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (4,3,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (4,8,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (5,1,1);
INSERT INTO event_equipment (event_id, equipment_id, quantity) VALUES (5,2,1);
-- -----------------------------------------------------
-- Table `boklach`.`order`
-- -----------------------------------------------------
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (9, 2, '2022-01-03 13:00:00', '10:00:00', 'Chernivtsi','Chernivetska', 'Holovna str. 224', 60000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (8, 3, '2022-01-30 19:00:00', '05:00:00', 'Kyiv','Kyivska', 'Hreshchatik str. 124', 15000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (1, 2, '2023-05-05 13:30:00', '11:00:00', 'Kharkiv','Kharkivska', 'Lvivska str. 20', 80000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (2, 1, '2022-09-07 16:00:00', '04:00:00', 'Lutsk','Volynska', 'Holovna str. 10', 12000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (4, 1, '2022-08-13 13:00:00', '03:30:00', 'Lviv','Lvivska', 'Horodotska str. 100', 10000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (10, 5, '2022-02-03 09:00:00', '02:00:00', 'Vinnitsya','Vinnitska', 'Chernivetska str. 15', 20000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (3, 4, '2022-11-11 15:00:00', '04:30:00', 'Lviv','Lvivska', 'Horodotska str. 112', 22000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (5, 1, '2023-01-10 15:20:00', '04:30:00', 'Ternopil','Ternopilska', 'Henry str. 14', 15000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (6, 2, '2022-05-05 14:00:00', '11:00:00', 'Poltava','Poltavska', 'Polty str. 31', 100000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_name, region_name, street_address, total_price)
	VALUES (7, 1, '2023-07-07 15:00:00', '3:00:00', 'Chernivtsi','Chernivetska', 'Nesalezhnosti blvd. 5', 18000);
-- -----------------------------------------------------
-- Table `boklach`.`order_agency_animator`
-- -----------------------------------------------------
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (1, 9, 6);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (1, 9, 7);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (2, 7, 4);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (3, 2, 2);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (4, 8, 3);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (4, 8, 6);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (4, 8, 9);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (5, 10, 1);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (5, 10, 10);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (6, 4, 9);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (7, 5, 2);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (7, 5, 5);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (8, 3, 5);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (8, 3, 7);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (8, 3, 8);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (9, 6, 7);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (9, 6, 10);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (10, 1, 1);
INSERT INTO order_agency_animator (order_id, agency_id, animator_id) VALUES (10, 1, 3);

ALTER TABLE agency ADD INDEX agency_name(name ASC);
ALTER TABLE animator ADD INDEX animator_surname(surname ASC);
ALTER TABLE client ADD INDEX client_surname(surname ASC);

SHOW INDEX FROM agency;
SHOW INDEX FROM animator;
SHOW INDEX FROM client;