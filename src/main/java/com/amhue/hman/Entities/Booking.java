package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Users users;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JsonBackReference
    private Room room;

    @OneToMany
    @JsonManagedReference
    private List<TableBooking> tableBookings;

    @OneToMany
    @JsonManagedReference
    private List<Bill> bills;

    public Booking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public List<TableBooking> getTableBookings() {
        return tableBookings;
    }

    public void setTableBookings(List<TableBooking> tableBookings) {
        this.tableBookings = tableBookings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(users, booking.users) && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && Objects.equals(room, booking.room) && Objects.equals(tableBookings, booking.tableBookings) && Objects.equals(bills, booking.bills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, startDate, endDate, room, tableBookings, bills);
    }

    public Booking(Integer id, Users users, LocalDate startDate, LocalDate endDate, Room room, List<TableBooking> tableBookings, List<Bill> bills) {
        this.id = id;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.tableBookings = tableBookings;
        this.bills = bills;
    }
}
