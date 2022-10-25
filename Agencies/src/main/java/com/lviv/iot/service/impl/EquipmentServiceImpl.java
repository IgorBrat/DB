package com.lviv.iot.service.impl;

import com.lviv.iot.dao.EquipmentDao;
import com.lviv.iot.domain.Equipment;
import com.lviv.iot.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    EquipmentDao equipmentDao;

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.findAll();
    }

    @Override
    public Optional<Equipment> findById(Integer id) {
        return equipmentDao.findById(id);
    }

    @Override
    public int create(Equipment equipment) {
        return equipmentDao.create(equipment);
    }

    @Override
    public int update(Integer id, Equipment equipment) {
        return equipmentDao.update(id, equipment);
    }

    @Override
    public int delete(Integer id) {
        return equipmentDao.delete(id);
    }
}
