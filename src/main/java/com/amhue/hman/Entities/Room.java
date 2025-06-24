package com.amhue.hman.Entities;

import java.util.List;
import java.util.Objects;

import com.amhue.hman.RoomType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {
    @Id @GeneratedValue private Integer id;

    @Column(unique = true) private Integer roomNumber;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JsonManagedReference
    private List<Booking> booking;

    @Enumerated(EnumType.STRING) RoomType roomType;

    public Room() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getRoomNumber() { return roomNumber; }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Booking> getBooking() { return booking; }

    public void setBooking(List<Booking> booking) { this.booking = booking; }

    public RoomType getRoomType() { return roomType; }

    public void setRoomType(RoomType roomType) { this.roomType = roomType; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room)o;
        return Objects.equals(id, room.id) &&
            Objects.equals(roomNumber, room.roomNumber) &&
            Objects.equals(booking, room.booking) && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomNumber, booking, roomType);
    }

    public Room(Integer id, Integer roomNumber, List<Booking> booking,
                RoomType roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.booking = booking;
        this.roomType = roomType;
    }
}
