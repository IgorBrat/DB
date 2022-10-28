package com.lviv.iot.service.impl;

import com.lviv.iot.repository.AnimatorRepository;
import com.lviv.iot.domain.Animator;
import com.lviv.iot.service.AnimatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimatorServiceImpl implements AnimatorService {
    @Autowired
    AnimatorRepository animatorRepository;

    @Override
    public List<Animator> findAll() {
        return animatorRepository.findAll();
    }

    @Override
    public Optional<Animator> findById(Integer id) {
        return animatorRepository.findById(id);
    }

    @Override
    public int create(Animator animator) {
        return animatorRepository.create(animator);
    }

    @Override
    public int update(Integer id, Animator animator) {
        return animatorRepository.update(id, animator);
    }

    @Override
    public int delete(Integer id) {
        return animatorRepository.delete(id);
    }
}
