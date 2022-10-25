package com.lviv.iot.controller.impl;

import com.lviv.iot.controller.RegionController;
import com.lviv.iot.domain.Region;
import com.lviv.iot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Controller
public class RegionControllerImpl implements RegionController {
    @Autowired
    RegionService regionService;

    @Override
    public List<Region> findAll() {
        return regionService.findAll();
    }

    @Override
    public Optional<Region> findById(String regionName) {
        return regionService.findById(regionName);
    }

    @Override
    public int create(Region region) {
        return regionService.create(region);
    }

    @Override
    public int update(String regionName, Region region) {
        return regionService.update(regionName, region);
    }

    @Override
    public int delete(String regionName) {
        return regionService.delete(regionName);
    }
}
