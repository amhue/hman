package com.amhue.hman;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Table {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime time;
    private int charge;

    @ManyToOne
    private Room room;

    public Table() {

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
        Table table = (Table) o;
        return charge == table.charge && Objects.equals(id, table.id) && Objects.equals(time, table.time) && Objects.equals(room, table.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, charge, room);
    }

    public Table(Integer id) {
        this.id = id;
    }
}
