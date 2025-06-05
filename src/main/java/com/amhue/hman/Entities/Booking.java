package com.amhue.hman.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Booking {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    private Users users;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Room room;

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

    public Booking(Integer id, Users users, LocalDate startDate, LocalDate endDate, Room room) {
        this.id = id;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
    }
}
