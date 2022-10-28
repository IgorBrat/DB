package com.lviv.iot.controller;

import com.lviv.iot.controller.GeneralController;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class AgencyControllerImpl implements GeneralController<Agency, Integer> {
    @Autowired
    private AgencyService agencyService;

    @Override
    public List<Agency> findAll() {
        return agencyService.findAll();
    }

    @Override
    public Optional<Agency> findById(Integer id) {
        return agencyService.findById(id);
    }

    @Override
    public int create(Agency agency) {
        return agencyService.create(agency);
    }

    @Override
    public int update(Integer id, Agency agency) {
        return agencyService.update(id, agency);
    }

    @Override
    public int delete(Integer id) {
        return agencyService.delete(id);
    }
}
