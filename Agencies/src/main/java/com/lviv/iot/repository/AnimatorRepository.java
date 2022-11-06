package com.lviv.iot.repository;

import com.lviv.iot.domain.Animator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AnimatorRepository extends JpaRepository<Animator, Integer> {
    @Query(value = "CALL CalcAverageSalary();", nativeQuery = true)
    BigDecimal getAverageSalary();
}
