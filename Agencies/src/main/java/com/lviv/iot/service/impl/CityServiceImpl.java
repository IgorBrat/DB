package com.lviv.iot.service.impl;

import com.lviv.iot.repository.CityRepository;
import com.lviv.iot.domain.City;
import com.lviv.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public int create(City city) {
        return cityRepository.create(city);
    }

    @Override
    public int update(String cityName, String regionName, City city) {
        return cityRepository.update(cityName, regionName, city);
    }

    @Override
    public int delete(String cityName, String regionName) {
        return cityRepository.delete(cityName, regionName);
    }
}
