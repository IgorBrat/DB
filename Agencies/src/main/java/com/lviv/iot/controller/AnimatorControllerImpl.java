package com.lviv.iot.controller;

import com.lviv.iot.controller.GeneralController;
import com.lviv.iot.domain.Animator;
import com.lviv.iot.service.AnimatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class AnimatorControllerImpl implements GeneralController<Animator, Integer> {
    @Autowired
    AnimatorService animatorService;

    @Override
    public List<Animator> findAll() {
        return animatorService.findAll();
    }

    @Override
    public Optional<Animator> findById(Integer id) {
        return animatorService.findById(id);
    }

    @Override
    public int create(Animator animator) {
        return animatorService.create(animator);
    }

    @Override
    public int update(Integer id, Animator animator) {
        return animatorService.update(id, animator);
    }

    @Override
    public int delete(Integer id) {
        return animatorService.delete(id);
    }
}
