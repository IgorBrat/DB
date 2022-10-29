package com.lviv.iot.dto.assembler;

import com.lviv.iot.controller.RegionController;
import com.lviv.iot.domain.Region;
import com.lviv.iot.dto.RegionDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RegionDtoAssembler implements RepresentationModelAssembler<Region, RegionDto> {

    @Override
    public RegionDto toModel(Region entity) {
        RegionDto regionDto = RegionDto.builder()
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(RegionController.class).getRegion(regionDto.getName())).withSelfRel();
        regionDto.add(selfLink);
        return regionDto;
    }

    @Override
    public CollectionModel<RegionDto> toCollectionModel(Iterable<? extends Region> entities) {
        CollectionModel<RegionDto> regionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RegionController.class).getAllRegions()).withSelfRel();
        regionDtos.add(selfLink);
        return regionDtos;
    }
}
