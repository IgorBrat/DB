package com.lviv.iot.controller.impl;

import com.lviv.iot.controller.CityController;
import com.lviv.iot.domain.City;
import com.lviv.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CityControllerImpl implements CityController {
    @Autowired
    private CityService cityService;

    @Override
    public List<City> findAll() {
        return cityService.findAll();
    }

    @Override
    public int create(City city) {
        return cityService.create(city);
    }

    @Override
    public int update(String cityName, String regionName, City city) {
        return cityService.update(cityName, regionName, city);
    }

    @Override
    public int delete(String cityName, String regionName) {
        return cityService.delete(cityName, regionName);
    }
}
