package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.CityDao;
import com.lviv.iot.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class CityDaoImpl implements CityDao {
    private static final String FIND_ALL = "SELECT * FROM city";
    private static final String CREATE = "INSERT city(city, region_name) VALUES (?)";
    private static final String UPDATE = "UPDATE city SET city=?";
    private static final String DELETE = "DELETE FROM city WHERE city=?";
    private static final String FIND_BY_ID = "SELECT * FROM city WHERE city=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(City.class));
    }

    @Override
    public Optional<City> findById(String cityName) {
        Optional<City> city;
        try {
            city = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(City.class), cityName));
        } catch (EmptyResultDataAccessException e) {
            city = Optional.empty();
        }
        return city;
    }

    @Override
    public int create(City city) {
        return jdbcTemplate.update(CREATE, city.getName(), city.getRegionName());
    }

    @Override
    public int update(String cityName, String regionName, City city) {
        return 0;
    }

    @Override
    public int delete(String s) {
        return 0;
    }
}
