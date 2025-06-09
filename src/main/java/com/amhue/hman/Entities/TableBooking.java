package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JsonIgnoreProperties("tableBookings")
    private Table table;

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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TableBooking that = (TableBooking) o;
        return Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(amount, that.amount) && Objects.equals(booking, that.booking) && Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, amount, booking, table);
    }

    public TableBooking(Integer id, LocalDateTime startTime, LocalDateTime endTime, Integer amount, Booking booking, Table table) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.booking = booking;
        this.table = table;
    }
}
