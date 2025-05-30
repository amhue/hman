package com.amhue.hman;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Room {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer room_number;
    private boolean isOccupied;

    @ManyToOne
    private Users customer;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<TableBooking> tableBookings;

    RoomType roomType;

    public Room() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public void setRoom_number(Integer room_number) {
        this.room_number = room_number;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
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
        return isOccupied == room.isOccupied && Objects.equals(id, room.id) && Objects.equals(room_number, room.room_number) && Objects.equals(customer, room.customer) && Objects.equals(bills, room.bills) && Objects.equals(tableBookings, room.tableBookings) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room_number, isOccupied, customer, bills, tableBookings, roomType);
    }

    public Room(Integer id, Integer room_number, boolean isOccupied, Users customer, List<Bill> bills, List<TableBooking> tableBookings, RoomType roomType) {
        this.id = id;
        this.room_number = room_number;
        this.isOccupied = isOccupied;
        this.customer = customer;
        this.bills = bills;
        this.tableBookings = tableBookings;
        this.roomType = roomType;
    }
}
