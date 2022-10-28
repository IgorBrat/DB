package com.lviv.iot.service.impl;

import com.lviv.iot.repository.RegionDao;
import com.lviv.iot.domain.Region;
import com.lviv.iot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionDao regionDao;

    @Override
    public List<Region> findAll() {
        return regionDao.findAll();
    }

    @Override
    public Optional<Region> findById(String regionName) {
        return regionDao.findById(regionName);
    }

    @Override
    public int create(Region region) {
        return regionDao.create(region);
    }

    @Override
    public int update(String regionName, Region region) {
        return regionDao.update(regionName, region);
    }

    @Override
    public int delete(String regionName) {
        return regionDao.delete(regionName);
    }
}
