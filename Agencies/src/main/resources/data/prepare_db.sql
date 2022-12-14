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
-- Table `boklach`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`city` ;

CREATE TABLE IF NOT EXISTS `boklach`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `region_name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`))
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
  `city_id` INT NOT NULL DEFAULT 1,
  `hq_address` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `agency_city_region`
    FOREIGN KEY (`city_id` )
    REFERENCES `boklach`.`city` (`id`),
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
  `city_id` INT NOT NULL,
  `salary_per_hour` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `animator_city`
    FOREIGN KEY (`city_id` )
    REFERENCES `boklach`.`city` (`id`),
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
  `discount_percentage` DECIMAL(4,2) NOT NULL,
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
-- Table `boklach`.`client`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`client` ;

CREATE TABLE IF NOT EXISTS `boklach`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL UNIQUE,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `city_id` INT NOT NULL,
  `street_address` VARCHAR(50) NOT NULL,
  `client_card_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `client_city`
    FOREIGN KEY (`city_id` )
    REFERENCES `boklach`.`city` (`id`),
  CONSTRAINT `client_client_card`
    FOREIGN KEY (`client_card_id`)
    REFERENCES `boklach`.`client_card` (`id`),
  CONSTRAINT `client_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `boklach`.`user` (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`equipment_shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`equipment_shop` ;

CREATE TABLE IF NOT EXISTS `boklach`.`equipment_shop` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `boklach`.`equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `boklach`.`equipment` ;

CREATE TABLE IF NOT EXISTS `boklach`.`equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `shop_id` INT NOT NULL,
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
  `city_id` INT NOT NULL,
  `street_address` VARCHAR(50) NOT NULL,
  `total_price` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `order_city`
    FOREIGN KEY (`city_id` )
    REFERENCES `boklach`.`city` (`id`),
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
INSERT INTO agency (user_id, name, owner, city_id, hq_address) 
	VALUES (11, 'LDB', 'Ivanov I.I.', 1, 'Holovna str. 3');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (12, 'HKeeper', 'Petrov P.P.', 2, 'Hreniv str. 4');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (13, 'NoNameFun', 'Ivanov I.I.', 3, 'Somiv str. 10');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (14, 'NEP', 'Petrov P.P.', 4, 'Somiv str. 100');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (15, 'GetFun', 'Ivanov I.I.', 5, 'Komarova str. 30');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (16, 'GH', 'Petrov P.P.', 6, 'Holovna str. 200');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (17, 'BPH', 'Petrov O.O.', 7, 'Nesalezhnosti blrd. 12');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (18, 'Wedd', 'Olegov O.O.', 8, 'Sone str. 40');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (19, 'Funnier', 'Petrov O.O.', 9, 'Shevchenka str. 55');
INSERT INTO agency (user_id, name, owner, city_id, hq_address)
	VALUES (20, 'Enterly', 'Ivanov I.I.', 10, 'Great blrd. 1');
-- -----------------------------------------------------
-- Table `boklach`.`animator`
-- -----------------------------------------------------
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour) 
	VALUES (1, 'Zoryan', 'Ivan', 1, 800);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (2, 'Zakharov', 'Zenovii', 2, 900);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (3, 'Karlo', 'Oleg', 3, 1100);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (4, 'Perta', 'Pinya', 4, 700);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (5, 'Perta', 'Pedro', 5, 600);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (6, 'Pepper', 'Petro', 6, 650);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (7, 'Mal', 'John', 7, 1000);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (8, 'Mal', 'Jack', 8, 800);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (9, 'Semkiv', 'Semen', 9, 900);
INSERT INTO animator (user_id, surname, name, city_id, salary_per_hour)
	VALUES (10, 'Chiv', 'Taras', 10, 750);
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
INSERT INTO client_card (name, discount_percentage) VALUES ("silver", 20);
INSERT INTO client_card (name, discount_percentage) VALUES ("silver plus", 30);
INSERT INTO client_card (name, discount_percentage) VALUES ("gold", 35);
INSERT INTO client_card (name, discount_percentage) VALUES ("gold plus", 50);
INSERT INTO client_card (name, discount_percentage) VALUES ("platinum", 60);
-- -----------------------------------------------------
-- Table `boklach`.`event`
-- -----------------------------------------------------
INSERT INTO event (name) VALUES ("birthday party");
INSERT INTO event (name) VALUES ("wedding");
INSERT INTO event (name) VALUES ("disco");
INSERT INTO event (name) VALUES ("magician concert");
INSERT INTO event (name) VALUES ("first school day party");
-- -----------------------------------------------------
-- Table `boklach`.`client`
-- -----------------------------------------------------
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (21, 'Bochko', 'Felicia', '1995-09-01', 1, 'Holovna str. 1', 1);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (22, 'Barinov', 'Oleksii', '1995-06-06', 2, 'Chernivetska str. 5', 5);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (23, 'Beryl', 'Bogdan', '1975-09-01', 3, 'Lvivska str. 10', NULL);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (24, 'Saman', 'Taras', '1970-01-05', 4, 'Nesalezhnosti blvd. 100', 3);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (25, 'Simov', 'Semen', '1990-03-10', 1, 'Nesalezhnosti blvd. 11', NULL);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (26, 'Gryniv', 'Marta', '1995-09-10', 6, 'Semka str. 15', 4);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (27, 'Gavra', 'Petro', NULL, 7, 'Sorov str. 20', 2);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (28, 'Avera', 'Ivan', '1960-09-09', 1, 'Ukrainska str. 200', 1);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id)  
	VALUES (29, 'Avera', 'Maria', NULL, 9, 'Sorov str. 13', 3);
INSERT INTO client (user_id, surname, name, birthday, city_id, street_address, client_card_id) 
	VALUES (30, 'Losa', 'Ivan', '2000-07-05', 1, 'Holovna str. 50', NULL);
-- -----------------------------------------------------
-- Table `boklach`.`equipment_shop`
-- -----------------------------------------------------
INSERT INTO equipment_shop (name) VALUES ('Beshketvill');
INSERT INTO equipment_shop (name) VALUES ('Magician site');
INSERT INTO equipment_shop (name) VALUES ('Presentvill');
INSERT INTO equipment_shop (name) VALUES ('ForYourPleasure');
-- -----------------------------------------------------
-- Table `boklach`.`equipment`
-- -----------------------------------------------------
INSERT INTO equipment (name, shop_id) VALUES ('micro', 2);
INSERT INTO equipment (name, shop_id) VALUES ('earphone', 3);
INSERT INTO equipment (name, shop_id) VALUES ('magic stick', 2);
INSERT INTO equipment (name, shop_id) VALUES ('balloon', 1);
INSERT INTO equipment (name, shop_id) VALUES ('carnaval mask', 2);
INSERT INTO equipment (name, shop_id) VALUES ('dj set', 4);
INSERT INTO equipment (name, shop_id) VALUES ('speaker', 3);
INSERT INTO equipment (name, shop_id) VALUES ('magic hat', 1);
INSERT INTO equipment (name, shop_id) VALUES ('synthesizer', 4);
INSERT INTO equipment (name, shop_id) VALUES ('cosplay costume', 3);
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
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (9, 2, '2022-01-03 13:00:00', '10:00:00', 1, 'Holovna str. 224', 60000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (8, 3, '2022-01-30 19:00:00', '05:00:00', 2, 'Hreshchatik str. 124', 15000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (1, 2, '2023-05-05 13:30:00', '11:00:00', 3, 'Lvivska str. 20', 80000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (2, 1, '2022-09-07 16:00:00', '04:00:00', 1, 'Holovna str. 10', 12000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (4, 1, '2022-08-13 13:00:00', '03:30:00', 5, 'Horodotska str. 100', 10000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (10, 5, '2022-02-03 09:00:00', '02:00:00', 6, 'Chernivetska str. 15', 20000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (3, 4, '2022-11-11 15:00:00', '04:30:00', 7, 'Horodotska str. 112', 22000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (5, 1, '2023-01-10 15:20:00', '04:30:00', 8, 'Henry str. 14', 15000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (6, 2, '2022-05-05 14:00:00', '11:00:00', 9, 'Polty str. 31', 100000);
INSERT INTO `order` (client_id, event_id, datetime, duration, city_id, street_address, total_price)
	VALUES (7, 1, '2023-07-07 15:00:00', '3:00:00', 10, 'Nesalezhnosti blvd. 5', 18000);
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


-- -----------------------------------------------------
-- TRIGGERS
-- -----------------------------------------------------

DELIMITER //
DROP TRIGGER IF EXISTS AddEquipmentCheckShop //
CREATE TRIGGER AddEquipmentCheckShop
    BEFORE INSERT
    ON `boklach`.`equipment` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
		SELECT id FROM `boklach`.`equipment_shop`
        WHERE id = NEW.shop_id
    ))
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Foreign key error: No equipment shop with such id';
END IF;
END //


DROP TRIGGER IF EXISTS UpdateEquipmentCheckShop //
CREATE TRIGGER UpdateEquipmentCheckShop
    BEFORE UPDATE
    ON `boklach`.`equipment` FOR EACH ROW
BEGIN
    IF(NOT EXISTS(
		SELECT id FROM `boklach`.`equipment_shop`
        WHERE id = NEW.shop_id
    ))
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Foreign key error: No equipment shop with such id';
END IF;
END //


DROP TRIGGER IF EXISTS UpdateEquipmentShopCheckId //
CREATE TRIGGER UpdateEquipmentShopCheckId
    BEFORE UPDATE
    ON `boklach`.`equipment_shop` FOR EACH ROW
BEGIN
    IF(EXISTS(
		SELECT shop_id FROM `boklach`.`equipment`
        WHERE shop_id = OLD.id
    ))
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Foreign key error: Can`t update row with record present in related table';
END IF;
END //


DROP TRIGGER IF EXISTS DeleteEquipmentShopCheckId //
CREATE TRIGGER DeleteEquipmentShopCheckId
    BEFORE DELETE
    ON `boklach`.`equipment_shop` FOR EACH ROW
BEGIN
    IF(EXISTS(
		SELECT shop_id FROM `boklach`.`equipment`
        WHERE shop_id = OLD.id
    ))
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Foreign key error: Can`t delete row with record present in related table';
END IF;
END //


DROP TRIGGER IF EXISTS CheckPhoneCardinalityInsert //
CREATE TRIGGER CheckPhoneCardinalityInsert
    BEFORE INSERT
    ON `boklach`.`user` FOR EACH ROW
BEGIN
    IF (LENGTH(NEW.phone) < 10)
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Value error: phone can`t be less 10 sybmols';
END IF;
END //


DROP TRIGGER IF EXISTS CheckPhoneCardinalityUpdate //
CREATE TRIGGER CheckPhoneCardinalityUpdate
    BEFORE UPDATE
    ON `boklach`.`user` FOR EACH ROW
BEGIN
    IF (LENGTH(NEW.phone) < 10)
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: phone can`t be less 10 sybmols';
END IF;
END //


DROP TRIGGER IF EXISTS CheckEmailInsert //
CREATE TRIGGER CheckEmailInsert
    BEFORE INSERT
    ON `boklach`.`user` FOR EACH ROW
BEGIN
    IF (NEW.email NOT RLIKE "^[a-zA-Z0-9][a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]*?[a-zA-Z0-9._-]?@[a-zA-Z0-9][a-zA-Z0-9._-]*?[a-zA-Z0-9]?\\.[a-zA-Z]{2,63}$")
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Value error: invalid email format';
END IF;
END //


DROP TRIGGER IF EXISTS CheckEmailUpdate //
CREATE TRIGGER CheckEmailUpdate
    BEFORE UPDATE
    ON `boklach`.`user` FOR EACH ROW
BEGIN
    IF (NEW.email NOT RLIKE "^[a-zA-Z0-9][a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]*?[a-zA-Z0-9._-]?@[a-zA-Z0-9][a-zA-Z0-9._-]*?[a-zA-Z0-9]?\\.[a-zA-Z]{2,63}$")
    THEN SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Value error: invalid email format';
END IF;
END //


DROP TRIGGER IF EXISTS ForbidDeleteOrder //
CREATE TRIGGER ForbidDeleteOrder
    BEFORE DELETE
    ON `boklach`.`order` FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Forbidden method: you can`t delete data from table `order`';
END //
DELIMITER ;


-- -----------------------------------------------------
-- CUSTOM FUNCTIONS
-- -----------------------------------------------------

DROP FUNCTION IF EXISTS GetAverageSalary;
DELIMITER //
CREATE FUNCTION GetAverageSalary()
RETURNS DECIMAL(8, 2)
DETERMINISTIC
BEGIN
	RETURN (SELECT AVG(salary_per_hour) FROM `animator`);
END //
DELIMITER ;


-- -----------------------------------------------------
-- STORED PROCEDURES
-- -----------------------------------------------------

DELIMITER //
DROP PROCEDURE IF EXISTS CityTestInserts //
CREATE PROCEDURE CityTestInserts (
	IN new_city_name VARCHAR(40),
    IN new_region_name VARCHAR(40))
BEGIN
	DECLARE max_id INT;
    DECLARE idx INT;
    SELECT MAX(id) + 1 INTO max_id FROM `city`;
    IF max_id IS NULL THEN SET max_id=1;
    END IF;
    SET idx = 1;
	label1: WHILE idx < 11 DO
        INSERT INTO `city` (name, region_name) VALUES (CONCAT(new_city_name, max_id), CONCAT(new_region_name, max_id));
        SET max_id = max_id + 1;
        SET idx = idx + 1;
        ITERATE label1;
	END WHILE;
END //


DROP PROCEDURE IF EXISTS UserParamInsert //
CREATE PROCEDURE UserParamInsert (
	IN new_phone VARCHAR(12),
    IN new_email VARCHAR(100))
BEGIN
	INSERT INTO `user` (phone, email) VALUES (new_phone, new_email);
    SELECT id, phone, email FROM `user` WHERE phone = new_phone;
END //


DROP PROCEDURE IF EXISTS CalcAverageSalary //
CREATE PROCEDURE CalcAverageSalary()
BEGIN
	SELECT GetAverageSalary() AS average_salary;
END //


DROP PROCEDURE IF EXISTS AddAnimatorAgencyRelationship //
CREATE PROCEDURE AddAnimatorAgencyRelationship(
	IN anim_surname VARCHAR(50),
    IN anim_name VARCHAR(50),
    IN ag_name VARCHAR(50),
    IN ag_owner VARCHAR(50))
BEGIN
	DECLARE ag_id, an_id INT;
    SELECT id INTO ag_id FROM `agency` WHERE name = ag_name AND owner = ag_owner;
    SELECT id INTO an_id FROM `animator` WHERE surname = anim_surname AND name = anim_name;
    IF (an_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Null value: no such animator found';
	END IF;
	IF (ag_id IS NULL)
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Null value: no such agency found';
	END IF;
	INSERT INTO `agency_animator` (agency_id, animator_id) VALUES (ag_id, an_id);
END //


DROP PROCEDURE IF EXISTS CreateTablesWithCursor //
CREATE PROCEDURE CreateTablesWithCursor()
BEGIN
	DECLARE done BOOL DEFAULT false;
    DECLARE agency_name VARCHAR(50);
    DECLARE my_cursor CURSOR
    FOR SELECT name FROM `agency`;
    
    DECLARE CONTINUE HANDLER
	FOR NOT FOUND SET done = true;
    
    OPEN my_cursor;
    my_loop: LOOP
		FETCH my_cursor INTO agency_name;
        IF (done = true) THEN LEAVE my_loop;
        END IF;
        SET @temp_query = CONCAT('CREATE TABLE IF NOT EXISTS ', agency_name, DATE_FORMAT(NOW(), "_%Y_%m_%d_%H_%i_%s"), ' (', agency_name, '1 INT, ', agency_name, '2 BOOL);');
		PREPARE my_query FROM @temp_query;
        EXECUTE my_query;
        DEALLOCATE PREPARE my_query;
    END LOOP;
    CLOSE my_cursor;
END //
DELIMITER ;