package com.amhue.hman.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private Integer id;

    private String billType;
    private Integer amount;

    @ManyToOne
    private Room room;

    public Bill() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) && Objects.equals(billType, bill.billType) && Objects.equals(amount, bill.amount) && Objects.equals(room, bill.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billType, amount, room);
    }

    public Bill(Integer id, String billType, Integer amount, Room room) {
        this.id = id;
        this.billType = billType;
        this.amount = amount;
        this.room = room;
    }
}
