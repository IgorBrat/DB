package com.lviv.iot.controller;

import com.lviv.iot.controller.GeneralController;
import com.lviv.iot.domain.Client;
import com.lviv.iot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientControllerImpl implements GeneralController<Client, Integer> {
    @Autowired
    ClientService clientService;

    @Override
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientService.findById(id);
    }

    @Override
    public int create(Client client) {
        return clientService.create(client);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientService.update(id, client);
    }

    @Override
    public int delete(Integer id) {
        return clientService.delete(id);
    }
}
