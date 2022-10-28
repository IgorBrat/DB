package com.lviv.iot.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UserPK implements Serializable {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phone")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String phone;
    @Column(name = "email")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String email;

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
        UserPK userPK = (UserPK) o;
        return Objects.equals(id, userPK.id) && Objects.equals(phone, userPK.phone) && Objects.equals(email, userPK.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, email);
    }
}
