package com.amhue.hman;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class User {
    @Id
    private Integer id;
    private String name;
    private String email;
    private String phone;

    private boolean isMgmt;

    public User() {

    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMgmt() {
        return isMgmt;
    }

    public void setMgmt(boolean mgmt) {
        isMgmt = mgmt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isMgmt == user.isMgmt && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, isMgmt);
    }

    public User(String name, String email, String phone, boolean isMgmt) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isMgmt = isMgmt;
    }
}
