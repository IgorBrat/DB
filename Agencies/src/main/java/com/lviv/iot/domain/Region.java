package com.lviv.iot.domain;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Region {
    @Id
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return Objects.equals(name, region.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

//    public Collection<City> getCitiesByName() {
//        return citiesByName;
//    }
//
//    public void setCitiesByName(Collection<City> citiesByName) {
//        this.citiesByName = citiesByName;
//    }
}
