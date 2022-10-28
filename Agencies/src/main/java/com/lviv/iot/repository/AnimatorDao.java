package com.lviv.iot.repository;

import com.lviv.iot.domain.Animator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimatorDao extends JpaRepository<Animator, Integer> {
}
