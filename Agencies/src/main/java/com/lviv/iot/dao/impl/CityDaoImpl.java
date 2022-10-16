package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.CityDao;
import com.lviv.iot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class CityDaoImpl implements CityDao {
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String CREATE = "INSERT city(name, region_name) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE city SET name=?, region_name=? WHERE name=? AND region_name=?";
    private static final String DELETE = "DELETE FROM city WHERE name=? AND region_name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public int create(City city) {
        return jdbcTemplate.update(CREATE, city.getName(), city.getRegionName());
    }

    @Override
    public int update(String cityName, String regionName, City city) {
        return jdbcTemplate.update(UPDATE, city.getName(), city.getRegionName(), cityName, regionName);
    }

    @Override
    public int delete(String cityName, String regionName) {
        return jdbcTemplate.update(DELETE, cityName, regionName);
    }
}
