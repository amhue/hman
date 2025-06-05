package com.amhue.hman.Entities;

import com.amhue.hman.RoomType;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer roomNumber;

    @OneToMany
    private List<Booking> booking;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<TableBooking> tableBookings;

    @Enumerated(EnumType.STRING)
    RoomType roomType;

    public Room() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Booking> getRoomDate() {
        return booking;
    }

    public void setRoomDate(List<Booking> booking) {
        this.booking = booking;
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

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id) && Objects.equals(roomNumber, room.roomNumber) && Objects.equals(booking, room.booking) && Objects.equals(bills, room.bills) && Objects.equals(tableBookings, room.tableBookings) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, booking, bills, tableBookings, roomType);
    }

    public Room(Integer id, Integer roomNumber, List<Booking> booking, List<Bill> bills, List<TableBooking> tableBookings, RoomType roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.booking = booking;
        this.bills = bills;
        this.tableBookings = tableBookings;
        this.roomType = roomType;
    }
}
