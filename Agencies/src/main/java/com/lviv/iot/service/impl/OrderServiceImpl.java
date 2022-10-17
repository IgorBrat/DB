package com.lviv.iot.service.impl;

import com.lviv.iot.dao.OrderDao;
import com.lviv.iot.domain.Order;
import com.lviv.iot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderDao.findById(id);
    }

    @Override
    public int create(Order order) {
        return orderDao.create(order);
    }

    @Override
    public int update(Integer id, Order order) {
        return orderDao.update(id, order);
    }

    @Override
    public int delete(Integer id) {
        return orderDao.delete(id);
    }
}
