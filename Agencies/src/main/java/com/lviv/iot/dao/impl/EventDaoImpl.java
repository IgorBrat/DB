package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.EventDao;
import com.lviv.iot.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EventDaoImpl implements EventDao {
    private static final String FIND_ALL = "SELECT * FROM event";
    private static final String CREATE = "INSERT event(name) VALUES (?)";
    private static final String UPDATE = "UPDATE event SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM event WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM event WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Event.class));
    }

    @Override
    public Optional<Event> findById(Integer id) {
        Optional<Event> event;
        try {
            event = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Event.class), id));
        } catch (EmptyResultDataAccessException e) {
            event = Optional.empty();
        }
        return event;
    }

    @Override
    public int create(Event event) {
        return jdbcTemplate.update(CREATE, event.getName());
    }

    @Override
    public int update(Integer id, Event event) {
        return jdbcTemplate.update(UPDATE, event.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
