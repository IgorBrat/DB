package com.lviv.iot.dto.assembler;

import com.lviv.iot.controller.EquipmentController;
import com.lviv.iot.domain.EventEquipment;
import com.lviv.iot.dto.EventEquipmentDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventEquipmentDtoAssembler implements RepresentationModelAssembler<EventEquipment, EventEquipmentDto> {
    @Override
    public EventEquipmentDto toModel(EventEquipment entity) {
        EventEquipmentDto eventEquipmentDto = EventEquipmentDto.builder()
                .equipmentName(entity.getEquipment().getName())
                .quantity(entity.getQuantity())
                .build();
//        Link selfLink = linkTo(methodOn(EquipmentController.class).getEquipment(entity.getEquipment().getId())).withSelfRel();
//        eventEquipmentDto.add(selfLink);
        return eventEquipmentDto;
    }

    public CollectionModel<EventEquipmentDto> toCollectionModel(Iterable<? extends EventEquipment> entities, Link link) {
        CollectionModel<EventEquipmentDto> eventEquipmentDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        eventEquipmentDtos.add(link);
        return eventEquipmentDtos;
    }
}
