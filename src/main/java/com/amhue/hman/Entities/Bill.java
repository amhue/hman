package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Objects;

@Entity
public class Bill {
    @Id
    @GeneratedValue
    private Integer id;

    private String billType;
    private Integer amount;

    @ManyToOne
    @JsonBackReference
    private Booking booking;

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

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id) && Objects.equals(billType, bill.billType) && Objects.equals(amount, bill.amount) && Objects.equals(booking, bill.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billType, amount, booking);
    }

    public Bill(Integer id, String billType, Integer amount, Booking booking) {
        this.id = id;
        this.billType = billType;
        this.amount = amount;
        this.booking = booking;
    }
}
