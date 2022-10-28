package com.lviv.iot.repository;

import com.lviv.iot.domain.Agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyDao extends JpaRepository<Agency, Integer> {
}
