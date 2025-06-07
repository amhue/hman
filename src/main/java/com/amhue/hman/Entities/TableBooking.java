package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TableBooking {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer amount;

    @ManyToOne
    @JsonBackReference
    private Booking booking;

    public TableBooking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
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
        TableBooking that = (TableBooking) o;
        return Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(amount, that.amount) && Objects.equals(booking, that.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, amount, booking);
    }

    public TableBooking(Integer id, LocalDateTime startTime, LocalDateTime endTime, Integer amount, Booking booking) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.booking = booking;
    }
}
