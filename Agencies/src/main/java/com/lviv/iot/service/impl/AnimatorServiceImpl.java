package com.lviv.iot.service.impl;

import com.lviv.iot.repository.AnimatorDao;
import com.lviv.iot.domain.Animator;
import com.lviv.iot.service.AnimatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimatorServiceImpl implements AnimatorService {
    @Autowired
    AnimatorDao animatorDao;

    @Override
    public List<Animator> findAll() {
        return animatorDao.findAll();
    }

    @Override
    public Optional<Animator> findById(Integer id) {
        return animatorDao.findById(id);
    }

    @Override
    public int create(Animator animator) {
        return animatorDao.create(animator);
    }

    @Override
    public int update(Integer id, Animator animator) {
        return animatorDao.update(id, animator);
    }

    @Override
    public int delete(Integer id) {
        return animatorDao.delete(id);
    }
}
