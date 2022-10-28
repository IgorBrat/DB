package com.lviv.iot.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "surname")
    private String surname;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "birthday")
    private Date birthday;
    @Basic
    @Column(name = "city_name")
    private String cityName;
    @Basic
    @Column(name = "region_name")
    private String regionName;
    @Basic
    @Column(name = "street_address")
    private String streetAddress;
    @OneToOne(mappedBy = "clientById")
    private User user;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false), @JoinColumn(name = "region_name", referencedColumnName = "region_name", nullable = false)})
    private City city;
    @ManyToOne
    @JoinColumn(name = "client_card_id", referencedColumnName = "id")
    private ClientCard clientCardByClientCardId;
    @OneToMany(mappedBy = "clientByClientId")
    private List<Order> ordersById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(surname, client.surname) && Objects.equals(name, client.name) && Objects.equals(birthday, client.birthday) && Objects.equals(cityName, client.cityName) && Objects.equals(regionName, client.regionName) && Objects.equals(streetAddress, client.streetAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, birthday, cityName, regionName, streetAddress);
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

    public ClientCard getClientCardByClientCardId() {
        return clientCardByClientCardId;
    }

    public void setClientCardByClientCardId(ClientCard clientCardByClientCardId) {
        this.clientCardByClientCardId = clientCardByClientCardId;
    }

    public List<Order> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(List<Order> ordersById) {
        this.ordersById = ordersById;
    }
}
