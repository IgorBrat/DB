package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.EquipmentDao;
import com.lviv.iot.domain.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EquipmentDaoImpl implements EquipmentDao {
    private static final String FIND_ALL = "SELECT * FROM equipment";
    private static final String CREATE = "INSERT equipment(name) VALUES (?)";
    private static final String UPDATE = "UPDATE equipment SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM equipment WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM equipment WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Equipment> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Equipment.class));
    }

    @Override
    public Optional<Equipment> findById(Integer id) {
        Optional<Equipment> equipment;
        try {
            equipment = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Equipment.class), id));
        } catch (EmptyResultDataAccessException e) {
            equipment = Optional.empty();
        }
        return equipment;
    }

    @Override
    public int create(Equipment equipment) {
        return jdbcTemplate.update(CREATE, equipment.getName());
    }

    @Override
    public int update(Integer id, Equipment equipment) {
        return jdbcTemplate.update(UPDATE, equipment.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
