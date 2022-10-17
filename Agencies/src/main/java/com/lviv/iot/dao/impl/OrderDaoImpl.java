package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.OrderDao;
import com.lviv.iot.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final String FIND_ALL = "SELECT * FROM `order`";
    private static final String CREATE = "INSERT `order`(client_id,event_id,datetime,duration,city_name,region_name,street_address,total_price) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE `order` SET client_id=?,event_id=?,datetime=?,city_name=?," +
            "region_name=?,hq_address=? WHERE id=?";
    private static final String DELETE = "DELETE FROM `order` WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM `order` WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public Optional<Order> findById(Integer id) {
        Optional<Order> order;
        try {
            order = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Order.class), id));
        } catch (EmptyResultDataAccessException e) {
            order = Optional.empty();
        }
        return order;
    }

    @Override
    public int create(Order order) {
        return jdbcTemplate.update(CREATE, order.getClientId(), order.getEventId(), order.getDatetime(),
                order.getDuration(), order.getCityName(), order.getRegionName(),
                order.getStreetAddress(), order.getTotalPrice());
    }

    @Override
    public int update(Integer id, Order order) {
        return jdbcTemplate.update(UPDATE, order.getClientId(), order.getEventId(), order.getDatetime(),
                order.getDuration(), order.getCityName(), order.getRegionName(),
                order.getStreetAddress(), order.getTotalPrice(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
