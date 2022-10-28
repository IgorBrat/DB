package com.lviv.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "eventByEventId")
    private List<Order> ordersById;
    @ManyToMany(mappedBy = "events")
    private Set<ClientCard> clientCards;
    @ManyToMany(mappedBy = "events")
    private Set<Equipment> equipments;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) && Objects.equals(name, event.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Order> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(List<Order> ordersById) {
        this.ordersById = ordersById;
    }

    public Set<ClientCard> getClientCards() {
        return clientCards;
    }

    public void setClientCards(Set<ClientCard> clientCards) {
        this.clientCards = clientCards;
    }

    public Set<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(Set<Equipment> equipments) {
        this.equipments = equipments;
    }
}
