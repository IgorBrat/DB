package com.lviv.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Agency {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "owner")
    private String owner;
    @Basic
    @Column(name = "city_name")
    private String cityName;
    @Basic
    @Column(name = "region_name")
    private String regionName;
    @Basic
    @Column(name = "hq_address")
    private String hqAddress;
    @OneToOne(mappedBy = "agencyById")
    private User user;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false), @JoinColumn(name = "region_name", referencedColumnName = "region_name", nullable = false)})
    private City city;
    @ManyToMany
    @JoinTable(name = "agency_animator", catalog = "", schema = "boklach", joinColumns = @JoinColumn(name = "agency_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "animator_id", referencedColumnName = "id", nullable = false))
    private Set<Animator> animators;
    @OneToMany(mappedBy = "agency")
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getHqAddress() {
        return hqAddress;
    }

    public void setHqAddress(String hqAddress) {
        this.hqAddress = hqAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(id, agency.id) && Objects.equals(name, agency.name) && Objects.equals(owner, agency.owner) && Objects.equals(cityName, agency.cityName) && Objects.equals(regionName, agency.regionName) && Objects.equals(hqAddress, agency.hqAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, cityName, regionName, hqAddress);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Set<Animator> getAnimators() {
        return animators;
    }

    public void setAnimators(Set<Animator> animators) {
        this.animators = animators;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
