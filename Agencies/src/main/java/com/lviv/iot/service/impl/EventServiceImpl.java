package com.lviv.iot.service.impl;

import com.lviv.iot.repository.EventRepository;
import com.lviv.iot.domain.Event;
import com.lviv.iot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventRepository.findById(id);
    }

    @Override
    public int create(Event event) {
        return eventRepository.create(event);
    }

    @Override
    public int update(Integer id, Event event) {
        return eventRepository.update(id, event);
    }

    @Override
    public int delete(Integer id) {
        return eventRepository.delete(id);
    }
}
