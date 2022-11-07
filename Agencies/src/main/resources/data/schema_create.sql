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