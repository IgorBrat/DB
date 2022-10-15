package com.lviv.iot.controller;

import com.lviv.iot.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityController {
    List<City> findAll();
    int create(City city);
    int update(String cityName, String regionName, City city);
    int delete(String cityName, String regionName);
}
