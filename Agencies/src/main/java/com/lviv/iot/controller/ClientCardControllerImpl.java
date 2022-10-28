package com.lviv.iot.controller;

import com.lviv.iot.controller.GeneralController;
import com.lviv.iot.domain.ClientCard;
import com.lviv.iot.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientCardControllerImpl implements GeneralController<ClientCard, Integer> {
    @Autowired
    ClientCardService clientCardService;

    @Override
    public List<ClientCard> findAll() {
        return clientCardService.findAll();
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        return clientCardService.findById(id);
    }

    @Override
    public int create(ClientCard clientCard) {
        return clientCardService.create(clientCard);
    }

    @Override
    public int update(Integer id, ClientCard clientCard) {
        return clientCardService.update(id, clientCard);
    }

    @Override
    public int delete(Integer id) {
        return clientCardService.delete(id);
    }
}
