package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

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
    private List<Bill> bills;

    public Booking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate end) {
        this.endDate = end;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(users, booking.users) && Objects.equals(startDate, booking.startDate) && Objects.equals(endDate, booking.endDate) && Objects.equals(room, booking.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, startDate, endDate, room);
    }

    public Booking(Integer id, Users users, LocalDate startDate, LocalDate endDate, Room room, List<Bill> bills) {
        this.id = id;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.bills = bills;
    }
}
