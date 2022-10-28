package com.lviv.iot.service.impl;

import com.lviv.iot.repository.RegionRepository;
import com.lviv.iot.domain.Region;
import com.lviv.iot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Region findById(String regionName) {
        return regionRepository.findById(regionName)
                .orElseThrow();
    }

    @Override
    public Region create(Region region) {
        return regionRepository.save(region);
    }

    // I don`t think it will work as region_name is its` own id
    @Override
    public void update(String regionName, Region newRegion) {
        Region region = regionRepository.findById(regionName)
                .orElseThrow();
        region.setName(regionName);
        regionRepository.save(region);
    }

    @Override
    public void delete(String regionName) {
        Region region = regionRepository.findById(regionName)
                .orElseThrow();
        regionRepository.delete(region);
    }
}
