package com.lviv.iot.controller;

import com.lviv.iot.controller.GeneralController;
import com.lviv.iot.domain.Event;
import com.lviv.iot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class EventControllerImpl implements GeneralController<Event, Integer> {
    @Autowired
    EventService eventService;

    @Override
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventService.findById(id);
    }

    @Override
    public int create(Event event) {
        return eventService.create(event);
    }

    @Override
    public int update(Integer id, Event event) {
        return eventService.update(id, event);
    }

    @Override
    public int delete(Integer id) {
        return eventService.delete(id);
    }
}
