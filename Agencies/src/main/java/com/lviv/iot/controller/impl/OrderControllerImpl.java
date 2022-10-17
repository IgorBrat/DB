package com.lviv.iot.controller.impl;

import com.lviv.iot.controller.OrderController;
import com.lviv.iot.domain.Order;
import com.lviv.iot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderControllerImpl implements OrderController {
    @Autowired
    OrderService orderService;

    @Override
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderService.findById(id);
    }

    @Override
    public int create(Order order) {
        return orderService.create(order);
    }

    @Override
    public int update(Integer id, Order order) {
        return orderService.update(id, order);
    }

    @Override
    public int delete(Integer id) {
        return orderService.delete(id);
    }
}
