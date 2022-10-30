package com.lviv.iot.controller;

import com.lviv.iot.domain.City;
import com.lviv.iot.dto.CityDto;
import com.lviv.iot.dto.assembler.CityDtoAssembler;
import com.lviv.iot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "cities")
public class CityController {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityDtoAssembler cityDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CityDto>> getAllCities() {
        List<City> cities = cityService.findAll();
        CollectionModel<CityDto> cityDtos = cityDtoAssembler.toCollectionModel(cities);
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable Integer cityId) {
        City city = cityService.findById(cityId);
        CityDto cityDto = cityDtoAssembler.toModel(city);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }
}
