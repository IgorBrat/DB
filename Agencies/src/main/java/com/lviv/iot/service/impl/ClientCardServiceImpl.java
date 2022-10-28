package com.lviv.iot.service.impl;

import com.lviv.iot.repository.ClientCardRepository;
import com.lviv.iot.domain.ClientCard;
import com.lviv.iot.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCardServiceImpl implements ClientCardService {
    @Autowired
    ClientCardRepository clientCardRepository;

    @Override
    public List<ClientCard> findAll() {
        return clientCardRepository.findAll();
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        return clientCardRepository.findById(id);
    }

    @Override
    public int create(ClientCard clientCard) {
        return clientCardRepository.create(clientCard);
    }

    @Override
    public int update(Integer id, ClientCard clientCard) {
        return clientCardRepository.update(id, clientCard);
    }

    @Override
    public int delete(Integer id) {
        return clientCardRepository.delete(id);
    }
}
