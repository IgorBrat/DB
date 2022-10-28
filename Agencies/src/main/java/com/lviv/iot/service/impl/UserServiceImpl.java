package com.lviv.iot.service.impl;

import com.lviv.iot.repository.UserRepository;
import com.lviv.iot.domain.User;
import com.lviv.iot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public int create(User user) {
        return userRepository.create(user);
    }

    @Override
    public int update(Integer id, User user) {
        return userRepository.update(id, user);
    }

    @Override
    public int delete(Integer id) {
        return userRepository.delete(id);
    }
}
