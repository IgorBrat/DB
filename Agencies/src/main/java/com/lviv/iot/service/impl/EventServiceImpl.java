package com.lviv.iot.service.impl;

import com.lviv.iot.repository.EventDao;
import com.lviv.iot.domain.Event;
import com.lviv.iot.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventDao eventDao;

    @Override
    public List<Event> findAll() {
        return eventDao.findAll();
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return eventDao.findById(id);
    }

    @Override
    public int create(Event event) {
        return eventDao.create(event);
    }

    @Override
    public int update(Integer id, Event event) {
        return eventDao.update(id, event);
    }

    @Override
    public int delete(Integer id) {
        return eventDao.delete(id);
    }
}
