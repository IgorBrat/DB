package com.lviv.iot.service.impl;

import com.lviv.iot.repository.ClientRepository;
import com.lviv.iot.domain.Client;
import com.lviv.iot.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    @Override
    public int create(Client client) {
        return clientRepository.create(client);
    }

    @Override
    public int update(Integer id, Client client) {
        return clientRepository.update(id, client);
    }

    @Override
    public int delete(Integer id) {
        return clientRepository.delete(id);
    }
}
