package com.lviv.iot.domain;

import javax.persistence.*;
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
    @ManyToMany(mappedBy = "event")
    private Set<ClientCard> client_card;
    @ManyToMany(mappedBy = "event")
    private Set<Equipment> equipment;

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

    public Set<ClientCard> getClient_card() {
        return client_card;
    }

    public void setClient_card(Set<ClientCard> client_card) {
        this.client_card = client_card;
    }

    public Set<Equipment> getEquipment() {
        return equipment;
    }

    public void setEquipment(Set<Equipment> equipment) {
        this.equipment = equipment;
    }
}
