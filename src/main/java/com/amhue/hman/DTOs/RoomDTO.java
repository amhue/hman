package com.amhue.hman.DTOs;

import com.amhue.hman.RoomType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RoomDTO {
    private Integer roomNo;
    @Enumerated(EnumType.STRING) private RoomType roomType;
    private Integer price;
    private String description;
    private boolean isOccupied;
    private Integer bookings;

    public Integer getRoomNo() { return roomNo; }
    public void setRoomNo(Integer roomNo) { this.roomNo = roomNo; }
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    public Integer getBookings() { return bookings; }
    public void setBookings(Integer bookings) { this.bookings = bookings; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roomNo == null) ? 0 : roomNo.hashCode());
        result =
            prime * result + ((roomType == null) ? 0 : roomType.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result +
                 ((description == null) ? 0 : description.hashCode());
        result = prime * result + (isOccupied ? 1231 : 1237);
        result =
            prime * result + ((bookings == null) ? 0 : bookings.hashCode());
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
        RoomDTO other = (RoomDTO)obj;
        if (roomNo == null) {
            if (other.roomNo != null)
                return false;
        } else if (!roomNo.equals(other.roomNo))
            return false;
        if (roomType != other.roomType)
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (isOccupied != other.isOccupied)
            return false;
        if (bookings == null) {
            if (other.bookings != null)
                return false;
        } else if (!bookings.equals(other.bookings))
            return false;
        return true;
    }

    public RoomDTO(Integer roomNo, RoomType roomType, Integer price,
                   String description, boolean isOccupied, Integer bookings) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.price = price;
        this.description = description;
        this.isOccupied = isOccupied;
        this.bookings = bookings;
    }
}
