package com.lviv.iot.repository;

import com.lviv.iot.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCardDao extends JpaRepository<ClientCard, Integer> {
}
