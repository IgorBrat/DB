package com.lviv.iot.service.impl;

import com.lviv.iot.dao.CityDao;
import com.lviv.iot.domain.City;
import com.lviv.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> findAll() {
        return cityDao.findAll();
    }

    @Override
    public int create(City city) {
        return cityDao.create(city);
    }

    @Override
    public int update(String cityName, String regionName, City city) {
        return cityDao.update(cityName, regionName, city);
    }

    @Override
    public int delete(String cityName, String regionName) {
        return cityDao.delete(cityName, regionName);
    }
}
