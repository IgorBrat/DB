package com.lviv.iot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(UserPK.class)
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "phone")
    private String phone;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "email")
    private String email;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", nullable = false)
    private Agency agencyById;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", nullable = false)
    private Animator animatorById;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", nullable = false)
    private Client clientById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(phone, user.phone) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, email);
    }

    public Agency getAgencyById() {
        return agencyById;
    }

    public void setAgencyById(Agency agencyById) {
        this.agencyById = agencyById;
    }

    public Animator getAnimatorById() {
        return animatorById;
    }

    public void setAnimatorById(Animator animatorById) {
        this.animatorById = animatorById;
    }

    public Client getClientById() {
        return clientById;
    }

    public void setClientById(Client clientById) {
        this.clientById = clientById;
    }
}
