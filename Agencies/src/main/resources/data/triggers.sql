USE `boklach`;

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


DROP TRIGGER IF EXISTS CheckPhoneCardinality //
CREATE TRIGGER CheckPhoneCardinality
	BEFORE INSERT
	ON `boklach`.`user` FOR EACH ROW
BEGIN
	IF (LENGTH(NEW.phone) < 10)
    THEN SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Value error: phone can`t be less 10 sybmols';
	END IF;
END //


DROP TRIGGER IF EXISTS CheckEmail //
CREATE TRIGGER CheckEmail
	BEFORE INSERT
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