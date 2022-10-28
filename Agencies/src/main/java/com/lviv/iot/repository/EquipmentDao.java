package com.lviv.iot.repository;

import com.lviv.iot.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, Integer> {
}
