USE `boklach`;

DROP PROCEDURE IF EXISTS CityTestInserts;
DELIMITER //
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
DELIMITER ;

DROP PROCEDURE IF EXISTS UserParamInsert;
DELIMITER //
CREATE PROCEDURE UserParamInsert (
	IN new_phone VARCHAR(12),
    IN new_email VARCHAR(100))
BEGIN
	INSERT INTO `user` (phone, email) VALUES (new_phone, new_email);
    SELECT id, phone, email FROM `user` WHERE phone = new_phone;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS CalcAverageSalary;
DELIMITER //
CREATE PROCEDURE CalcAverageSalary()
BEGIN
	DECLARE label VARCHAR(20);
	SELECT GetAverageSalary() AS average_salary;
END //
DELIMITER ;