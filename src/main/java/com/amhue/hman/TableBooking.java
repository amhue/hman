package com.amhue.hman;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TableBooking {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime time;
    private int charge;

    @ManyToOne
    private Room room;

    public TableBooking() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
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
        TableBooking tableBooking = (TableBooking) o;
        return charge == tableBooking.charge && Objects.equals(id, tableBooking.id) && Objects.equals(time, tableBooking.time) && Objects.equals(room, tableBooking.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, charge, room);
    }

    public TableBooking(Integer id) {
        this.id = id;
    }
}
