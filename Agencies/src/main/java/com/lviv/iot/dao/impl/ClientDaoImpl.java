package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.ClientDao;
import com.lviv.iot.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL = "SELECT * FROM client";
    private static final String CREATE = "INSERT client(user_id,surname,name,birthday,city_name,region_name,street_address,client_card_id) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE client SET user_id=?,surname=?,name=?,birthday=?," +
            "city_name=?,region_name=?,street_address=?,client_card_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM client WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM client WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> findById(Integer id) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Client.class), id));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(Client client) {
        return jdbcTemplate.update(CREATE, client.getUserId(), client.getSurname(), client.getName(),
                client.getBirthday(), client.getCityName(), client.getRegionName(),
                client.getStreetAddress(), client.getClientCardId());
    }

    @Override
    public int update(Integer id, Client client) {
        return jdbcTemplate.update(UPDATE, client.getUserId(), client.getSurname(), client.getName(),
                client.getBirthday(), client.getCityName(), client.getRegionName(),
                client.getStreetAddress(), client.getClientCardId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
