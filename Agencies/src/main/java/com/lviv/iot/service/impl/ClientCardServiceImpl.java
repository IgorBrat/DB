package com.lviv.iot.service.impl;

import com.lviv.iot.repository.ClientCardDao;
import com.lviv.iot.domain.ClientCard;
import com.lviv.iot.service.ClientCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientCardServiceImpl implements ClientCardService {
    @Autowired
    ClientCardDao clientCardDao;

    @Override
    public List<ClientCard> findAll() {
        return clientCardDao.findAll();
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        return clientCardDao.findById(id);
    }

    @Override
    public int create(ClientCard clientCard) {
        return clientCardDao.create(clientCard);
    }

    @Override
    public int update(Integer id, ClientCard clientCard) {
        return clientCardDao.update(id, clientCard);
    }

    @Override
    public int delete(Integer id) {
        return clientCardDao.delete(id);
    }
}
