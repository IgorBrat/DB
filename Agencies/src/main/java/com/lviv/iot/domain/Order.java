package com.lviv.iot.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "datetime")
    private Timestamp datetime;
    @Basic
    @Column(name = "duration")
    private Time duration;
    @Basic
    @Column(name = "street_address")
    private String streetAddress;
    @Basic
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private Client clientByClientId;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event eventByEventId;
    @ManyToOne
    @JoinColumns({@JoinColumn(name = "city_name", referencedColumnName = "name", nullable = false), @JoinColumn(name = "region_name", referencedColumnName = "region_name", nullable = false)})
    private City city;
    @ManyToOne
    @JoinTable(name = "order_agency_animator", catalog = "", schema = "boklach", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "agency_id", referencedColumnName = "id", nullable = false))
    private Agency agency;
    @ManyToMany
    @JoinTable(name = "order_agency_animator", catalog = "", schema = "boklach", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "animator_id", referencedColumnName = "id", nullable = false))
    private Set<Animator> animators;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(datetime, order.datetime) && Objects.equals(duration, order.duration) && Objects.equals(streetAddress, order.streetAddress) && Objects.equals(totalPrice, order.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datetime, duration, streetAddress, totalPrice);
    }

    public Client getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(Client clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public Event getEventByEventId() {
        return eventByEventId;
    }

    public void setEventByEventId(Event eventByEventId) {
        this.eventByEventId = eventByEventId;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Set<Animator> getAnimators() {
        return animators;
    }

    public void setAnimators(Set<Animator> animators) {
        this.animators = animators;
    }
}
