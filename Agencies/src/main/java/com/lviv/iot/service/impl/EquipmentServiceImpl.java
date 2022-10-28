package com.lviv.iot.service.impl;

import com.lviv.iot.repository.EquipmentRepository;
import com.lviv.iot.domain.Equipment;
import com.lviv.iot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentRepository equipmentRepository;

    @Override
    public List<Equipment> findAll() {
        return equipmentRepository.findAll();
    }

    @Override
    public Optional<Equipment> findById(Integer id) {
        return equipmentRepository.findById(id);
    }

    @Override
    public int create(Equipment equipment) {
        return equipmentRepository.create(equipment);
    }

    @Override
    public int update(Integer id, Equipment equipment) {
        return equipmentRepository.update(id, equipment);
    }

    @Override
    public int delete(Integer id) {
        return equipmentRepository.delete(id);
    }
}
