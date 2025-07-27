package com.amhue.hman;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class RoomDTO {
    private Integer roomNo;
    @Enumerated(EnumType.STRING) private RoomType roomType;
    private boolean isOccupied;
    private Integer bookings;

    public Integer getRoomNo() { return roomNo; }
    public void setRoomNo(Integer roomNo) { this.roomNo = roomNo; }
    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }
    public Integer getBookings() { return bookings; }
    public void setBookings(Integer bookings) { this.bookings = bookings; }

    public RoomDTO(Integer roomNo, RoomType roomType, boolean isOccupied,
                   Integer bookings) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.isOccupied = isOccupied;
        this.bookings = bookings;
    }
}
