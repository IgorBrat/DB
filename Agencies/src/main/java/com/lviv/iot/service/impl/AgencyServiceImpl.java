package com.lviv.iot.service.impl;

import com.lviv.iot.repository.AgencyRepository;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public List<Agency> findAll() {
        return agencyRepository.findAll();
    }

    @Override
    public Optional<Agency> findById(Integer id) {
        return agencyRepository.findById(id);
    }

    @Override
    public int create(Agency agency) {
        return agencyRepository.create(agency);
    }

    @Override
    public int update(Integer id, Agency agency) {
        return agencyRepository.update(id, agency);
    }

    @Override
    public int delete(Integer id) {
        return agencyRepository.delete(id);
    }
}
