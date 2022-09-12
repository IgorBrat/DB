-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boklach
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema boklach
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boklach` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `boklach` ;

-- -----------------------------------------------------
-- Table `boklach`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`city` (
  `name` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`agency`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`agency` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `owner` VARCHAR(50) NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `hq_address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `agency_city` (`city_name` ASC) VISIBLE,
  CONSTRAINT `agency_city`
    FOREIGN KEY (`city_name`)
    REFERENCES `boklach`.`city` (`name`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`animator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`animator` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `salary_per_hour` DECIMAL(7,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `animator_city` (`city_name` ASC) VISIBLE,
  CONSTRAINT `animator_city`
    FOREIGN KEY (`city_name`)
    REFERENCES `boklach`.`city` (`name`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`agency_animator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`agency_animator` (
  `agency_id` INT NOT NULL,
  `animator_id` INT NOT NULL,
  PRIMARY KEY (`animator_id`, `agency_id`),
  INDEX `animator_agency_agency` (`agency_id` ASC) VISIBLE,
  CONSTRAINT `animator_agency_agency`
    FOREIGN KEY (`agency_id`)
    REFERENCES `boklach`.`agency` (`id`),
  CONSTRAINT `animator_agency_animator`
    FOREIGN KEY (`animator_id`)
    REFERENCES `boklach`.`animator` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`client_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`client_card` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`card_event_discount`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`card_event_discount` (
  `client_card_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `discount_percentage` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`client_card_id`, `event_id`),
  INDEX `card_event_discount_event` (`event_id` ASC) VISIBLE,
  CONSTRAINT `card_event_discount_client_card`
    FOREIGN KEY (`client_card_id`)
    REFERENCES `boklach`.`client_card` (`id`),
  CONSTRAINT `card_event_discount_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`client` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `birthday` DATE NULL DEFAULT NULL,
  `phone` VARCHAR(12) NOT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `street_address` VARCHAR(50) NULL DEFAULT NULL,
  `client_card_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `client_city` (`city_name` ASC) VISIBLE,
  INDEX `client_client_card` (`client_card_id` ASC) VISIBLE,
  CONSTRAINT `client_city`
    FOREIGN KEY (`city_name`)
    REFERENCES `boklach`.`city` (`name`),
  CONSTRAINT `client_client_card`
    FOREIGN KEY (`client_card_id`)
    REFERENCES `boklach`.`client_card` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`equipment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`event_equipment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`event_equipment` (
  `event_id` INT NOT NULL,
  `equipment_id` INT NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`event_id`, `equipment_id`),
  INDEX `event_equipment_equipment` (`equipment_id` ASC) VISIBLE,
  CONSTRAINT `event_equipment_equipment`
    FOREIGN KEY (`equipment_id`)
    REFERENCES `boklach`.`equipment` (`id`),
  CONSTRAINT `event_equipment_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `event_id` INT NOT NULL,
  `datetime` TIMESTAMP NOT NULL,
  `duration` TIME NOT NULL,
  `city_name` VARCHAR(40) NOT NULL,
  `street_address` VARCHAR(50) NOT NULL,
  `total_price` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `order_city` (`city_name` ASC) VISIBLE,
  INDEX `order_client` (`client_id` ASC) VISIBLE,
  INDEX `order_event` (`event_id` ASC) VISIBLE,
  CONSTRAINT `order_city`
    FOREIGN KEY (`city_name`)
    REFERENCES `boklach`.`city` (`name`),
  CONSTRAINT `order_client`
    FOREIGN KEY (`client_id`)
    REFERENCES `boklach`.`client` (`id`),
  CONSTRAINT `order_event`
    FOREIGN KEY (`event_id`)
    REFERENCES `boklach`.`event` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `boklach`.`order_agency_animator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boklach`.`order_agency_animator` (
  `order_id` INT NOT NULL,
  `agency_id` INT NOT NULL,
  `animator_id` INT NOT NULL,
  PRIMARY KEY (`order_id`, `agency_id`, `animator_id`),
  INDEX `agency_animator` (`agency_id` ASC, `animator_id` ASC) VISIBLE,
  CONSTRAINT `agency_animator`
    FOREIGN KEY (`agency_id` , `animator_id`)
    REFERENCES `boklach`.`agency_animator` (`agency_id` , `animator_id`),
  CONSTRAINT `order_agency_animator_order`
    FOREIGN KEY (`order_id`)
    REFERENCES `boklach`.`order` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
