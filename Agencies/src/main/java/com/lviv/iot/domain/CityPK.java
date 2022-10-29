package com.lviv.iot.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CityPK implements Serializable {
    @Column(name = "name")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String name;
    @Column(name = "region_name")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String regionName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityPK cityPK = (CityPK) o;
        return Objects.equals(name, cityPK.name) && Objects.equals(regionName, cityPK.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, regionName);
    }
}