package com.amhue.hman;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Users {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;
    private String phone;

    private boolean isMgmt;

    public Users() {

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
        Users users = (Users) o;
        return isMgmt == users.isMgmt && Objects.equals(id, users.id) && Objects.equals(name, users.name) && Objects.equals(email, users.email) && Objects.equals(phone, users.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone, isMgmt);
    }

    public Users(String name, String email, String phone, boolean isMgmt) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isMgmt = isMgmt;
    }
}
