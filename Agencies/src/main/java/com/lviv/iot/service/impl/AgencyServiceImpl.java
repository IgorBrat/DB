package com.lviv.iot.service.impl;

import com.lviv.iot.repository.AgencyDao;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    private AgencyDao agencyDao;

    @Override
    public List<Agency> findAll() {
        return agencyDao.findAll();
    }

    @Override
    public Optional<Agency> findById(Integer id) {
        return agencyDao.findById(id);
    }

    @Override
    public int create(Agency agency) {
        return agencyDao.create(agency);
    }

    @Override
    public int update(Integer id, Agency agency) {
        return agencyDao.update(id, agency);
    }

    @Override
    public int delete(Integer id) {
        return agencyDao.delete(id);
    }
}
