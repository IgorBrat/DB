package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.ClientCardDao;
import com.lviv.iot.domain.ClientCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClientCardDaoImpl implements ClientCardDao {
    private static final String FIND_ALL = "SELECT * FROM client_card";
    private static final String CREATE = "INSERT client_card(name) VALUES (?)";
    private static final String UPDATE = "UPDATE client_card SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM client_card WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM client_card WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ClientCard> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ClientCard.class));
    }

    @Override
    public Optional<ClientCard> findById(Integer id) {
        Optional<ClientCard> clientCard;
        try {
            clientCard = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(ClientCard.class), id));
        } catch (EmptyResultDataAccessException e) {
            clientCard = Optional.empty();
        }
        return clientCard;
    }

    @Override
    public int create(ClientCard clientCard) {
        return jdbcTemplate.update(CREATE, clientCard.getName());
    }

    @Override
    public int update(Integer id, ClientCard clientCard) {
        return jdbcTemplate.update(UPDATE, clientCard.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}