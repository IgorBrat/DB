package com.lviv.iot.service.impl;

import com.lviv.iot.repository.OrderRepository;
import com.lviv.iot.domain.Order;
import com.lviv.iot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public int create(Order order) {
        return orderRepository.create(order);
    }

    @Override
    public int update(Integer id, Order order) {
        return orderRepository.update(id, order);
    }

    @Override
    public int delete(Integer id) {
        return orderRepository.delete(id);
    }
}
