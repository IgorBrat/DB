package com.lviv.iot.repository;

import com.lviv.iot.domain.City;
import com.lviv.iot.domain.CityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City, CityPK> {
}
