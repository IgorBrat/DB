package com.lviv.iot.service;

import com.lviv.iot.domain.Equipment;
import com.lviv.iot.domain.Event;

import java.util.Set;

public interface EventService extends GeneralService<Event, Integer> {
    public Set<Equipment> findEquipmentsById(Integer id);
}
