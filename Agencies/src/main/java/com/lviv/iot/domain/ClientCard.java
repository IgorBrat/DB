package com.lviv.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "client_card", schema = "boklach", catalog = "")
public class ClientCard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "discount_percentage")
    private Float discountPercentage;
    @OneToMany(mappedBy = "clientCardByClientCardId")
    private List<Client> clientsById;

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
        ClientCard that = (ClientCard) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Client> getClientsById() {
        return clientsById;
    }

    public void setClientsById(List<Client> clientsById) {
        this.clientsById = clientsById;
    }

}
