package com.lviv.iot.service;

import com.lviv.iot.domain.Agency;
import com.lviv.iot.domain.Animator;

import java.util.List;
import java.util.Set;

public interface AgencyService extends GeneralService<Agency, Integer> {
    public Agency create(Agency agency, Integer cityId, Integer userId);
    public void update(Integer id, Agency newAgency, Integer cityId, Integer userId);
    public List<Agency> findAgenciesByCityId(Integer cityId);
    public Set<Animator> findAnimatorsById(Integer id);
}
