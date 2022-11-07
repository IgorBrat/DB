USE `boklach`;

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
DELIMITER ;