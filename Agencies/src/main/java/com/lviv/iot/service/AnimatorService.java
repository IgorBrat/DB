package com.lviv.iot.service;

import com.lviv.iot.domain.Agency;
import com.lviv.iot.domain.Animator;

import java.util.List;
import java.util.Set;

public interface AnimatorService extends GeneralService<Animator, Integer> {
    public Animator create(Animator animator, Integer cityId, Integer userId);
    public void update(Integer id, Animator animator, Integer cityId, Integer userId);
    public List<Animator> findAnimatorsByCityId(Integer cityId);
    public Set<Agency> findAgenciesById(Integer id);
}
