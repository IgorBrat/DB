package com.lviv.iot.controller;

import com.lviv.iot.domain.Region;
import com.lviv.iot.dto.RegionDto;
import com.lviv.iot.dto.assembler.RegionDtoAssembler;
import com.lviv.iot.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "regions")
public class RegionController {
    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionDtoAssembler regionDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<RegionDto>> getAllRegions() {
        List<Region> regions = regionService.findAll();
        CollectionModel<RegionDto> regionDtos = regionDtoAssembler.toCollectionModel(regions);
        return new ResponseEntity<>(regionDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{regionName}")
    public ResponseEntity<RegionDto> getRegion(@PathVariable String regionName) {
        Region region = regionService.findById(regionName);
        RegionDto regionDto = regionDtoAssembler.toModel(region);
        return new ResponseEntity<>(regionDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<RegionDto> addRegion(@RequestBody Region region) {
        Region newRegion = regionService.create(region);
        RegionDto regionDto = regionDtoAssembler.toModel(newRegion);
        return new ResponseEntity<>(regionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{regionName}")
    public ResponseEntity<?> updateRegion(@PathVariable String regionName, @RequestBody Region region) {
        regionService.update(regionName, region);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{regionName}")
    public ResponseEntity<?> deleteRegion(@PathVariable String regionName) {
        regionService.delete(regionName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
