package com.lviv.iot.controller;

import com.lviv.iot.domain.Equipment;
import com.lviv.iot.domain.Event;
import com.lviv.iot.dto.EquipmentDto;
import com.lviv.iot.dto.EventDto;
import com.lviv.iot.dto.assembler.EquipmentDtoAssembler;
import com.lviv.iot.dto.assembler.EventDtoAssembler;
import com.lviv.iot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "events")
public class EventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventDtoAssembler eventDtoAssembler;
    @Autowired
    private EquipmentDtoAssembler equipmentDtoAssembler;

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<EventDto>> getAllEvents() {
        List<Event> events = eventService.findAll();
        CollectionModel<EventDto> eventDtos = eventDtoAssembler.toCollectionModel(events);
        return new ResponseEntity<>(eventDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDto> getEvent(@PathVariable Integer id) {
        Event event = eventService.findById(id);
        EventDto eventDto = eventDtoAssembler.toModel(event);
        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EventDto> addEvent(@RequestBody Event event) {
        Event newEvent = eventService.create(event);
        EventDto eventDto = eventDtoAssembler.toModel(newEvent);
        return new ResponseEntity<>(eventDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateEvent(@RequestBody Event uEvent, @PathVariable Integer id) {
        eventService.update(id, uEvent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/equipments")
    public ResponseEntity<CollectionModel<EquipmentDto>> getEquipmentsById(@PathVariable Integer id) {
        Set<Equipment> equipment = eventService.findEquipmentsById(id);
        Link selfLink = linkTo(methodOn(EventController.class).getEquipmentsById(id)).withSelfRel();
        CollectionModel<EquipmentDto> equipmentDtos = equipmentDtoAssembler.toCollectionModel(equipment, selfLink);
        return new ResponseEntity<>(equipmentDtos, HttpStatus.OK);
    }
}
