DELIMITER //
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
DELIMITER ;

DELIMITER //
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
DELIMITER ;

DELIMITER //
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
DELIMITER ;

DELIMITER //
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
DELIMITER ;