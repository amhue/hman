package com.amhue.hman.Entities;

import java.util.List;

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

    private String description;

    @Enumerated(EnumType.STRING) RoomType roomType;

    private Integer price;

    public Room() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getRoomNumber() { return roomNumber; }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Booking> getBooking() { return booking; }

    public void setBooking(List<Booking> booking) { this.booking = booking; }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomType getRoomType() { return roomType; }

    public void setRoomType(RoomType roomType) { this.roomType = roomType; }

    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result =
            prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
        result = prime * result +
                 ((description == null) ? 0 : description.hashCode());
        result =
            prime * result + ((roomType == null) ? 0 : roomType.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (roomNumber == null) {
            if (other.roomNumber != null)
                return false;
        } else if (!roomNumber.equals(other.roomNumber))
            return false;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (roomType != other.roomType)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    public Room(Integer id, Integer roomNumber, List<Booking> booking,
                String description, RoomType roomType, Integer price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.booking = booking;
        this.description = description;
        this.roomType = roomType;
        this.price = price;
    }
}
