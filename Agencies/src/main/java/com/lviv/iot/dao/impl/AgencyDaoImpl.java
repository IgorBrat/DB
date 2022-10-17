package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.AgencyDao;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class AgencyDaoImpl implements AgencyDao {
    private static final String FIND_ALL = "SELECT * FROM agency";
    private static final String CREATE = "INSERT agency(user_id,name,owner,city_name,region_name,hq_address) " +
            "VALUES (?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE agency SET user_id=?,name=?,owner=?,city_name=?," +
            "region_name=?,hq_address=? WHERE id=?";
    private static final String DELETE = "DELETE FROM agency WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM agency WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Agency> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Agency.class));
    }

    @Override
    public Optional<Agency> findById(Integer id) {
        Optional<Agency> agency;
        try {
            agency = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Agency.class), id));
        } catch (EmptyResultDataAccessException e) {
            agency = Optional.empty();
        }
        return agency;
    }

    @Override
    public int create(Agency agency) {
        return jdbcTemplate.update(CREATE, agency.getUserId(), agency.getName(), agency.getOwner(),
                agency.getCityName(), agency.getRegionName(), agency.getHqAddress());
    }

    @Override
    public int update(Integer id, Agency agency) {
        return jdbcTemplate.update(UPDATE, agency.getUserId(), agency.getName(), agency.getOwner(),
                agency.getCityName(), agency.getRegionName(), agency.getHqAddress(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
