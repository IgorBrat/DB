package com.lviv.iot.controller.impl;

import com.lviv.iot.controller.EquipmentController;
import com.lviv.iot.domain.Equipment;
import com.lviv.iot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EquipmentControllerImpl implements EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @Override
    public List<Equipment> findAll() {
        return equipmentService.findAll();
    }

    @Override
    public Optional<Equipment> findById(Integer id) {
        return equipmentService.findById(id);
    }

    @Override
    public int create(Equipment equipment) {
        return equipmentService.create(equipment);
    }

    @Override
    public int update(Integer id, Equipment equipment) {
        return equipmentService.update(id, equipment);
    }

    @Override
    public int delete(Integer id) {
        return equipmentService.delete(id);
    }
}
