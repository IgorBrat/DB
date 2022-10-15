package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.GeneralDao;
import com.lviv.iot.dao.RegionDao;
import com.lviv.iot.domain.City;
import com.lviv.iot.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class RegionDaoImpl implements RegionDao {
    private static final String FIND_ALL = "SELECT * FROM region";
    private static final String CREATE = "INSERT region(name) VALUES (?)";
    private static final String UPDATE = "UPDATE region SET region=? WHERE region=?";
    private static final String DELETE = "DELETE FROM region WHERE region=?";
    private static final String FIND_BY_ID = "SELECT * FROM region WHERE region=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Region> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Region.class));
    }

    @Override
    public Optional<Region> findById(String regionName) {
        Optional<Region> regions;
        try {
            regions = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Region.class), regionName));
        } catch (EmptyResultDataAccessException e) {
            regions = Optional.empty();
        }
        return regions;
    }

    @Override
    public int create(Region region) {
        return jdbcTemplate.update(CREATE, region.getName());
    }

    @Override
    public int update(String regionName, Region region) {
        return jdbcTemplate.update(UPDATE, region.getName(), regionName);
    }

    @Override
    public int delete(String regionName) {
        return jdbcTemplate.update(DELETE, regionName);
    }
}
