package com.lviv.iot.dao.impl;

import com.lviv.iot.dao.AnimatorDao;
import com.lviv.iot.domain.Agency;
import com.lviv.iot.domain.Animator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimatorDaoImpl implements AnimatorDao {
    private static final String FIND_ALL = "SELECT * FROM animator";
    private static final String CREATE = "INSERT animator(user_id,surname,name,email,phone,city_name,region_name,salary_per_hour) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE animator SET user_id=?,surname=?,name=?,email=?,phone=?," +
            "city_name=?,region_name=?,salary_per_hour=? WHERE id=?";
    private static final String DELETE = "DELETE FROM animator WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM animator WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Animator> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Animator.class));
    }

    @Override
    public Optional<Animator> findById(Integer id) {
        Optional<Animator> animator;
        try {
            animator = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Animator.class), id));
        } catch (EmptyResultDataAccessException e) {
            animator = Optional.empty();
        }
        return animator;
    }

    @Override
    public int create(Animator animator) {
        return jdbcTemplate.update(CREATE, animator.getUserId(), animator.getSurname(), animator.getName(),
                animator.getEmail(), animator.getPhone(), animator.getCityName(), animator.getRegionName(),
                animator.getSalaryPerHour());
    }

    @Override
    public int update(Integer id, Animator animator) {
        return jdbcTemplate.update(UPDATE, animator.getUserId(), animator.getSurname(), animator.getName(),
                animator.getEmail(), animator.getPhone(), animator.getCityName(), animator.getRegionName(),
                animator.getSalaryPerHour(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
