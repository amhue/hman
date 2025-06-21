package com.amhue.hman;

import java.time.LocalDate;

public class RoomSearchDTO {
    private RoomType roomType;
    private LocalDate start;
    private LocalDate end;

    public RoomType getRoomType() { return roomType; }
    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
    public LocalDate getStart() { return start; }
    public void setStart(LocalDate start) { this.start = start; }
    public LocalDate getEnd() { return end; }
    public void setEnd(LocalDate end) { this.end = end; }

    public RoomSearchDTO(RoomType roomType, LocalDate start, LocalDate end) {
        this.roomType = roomType;
        this.start = start;
        this.end = end;
    }

    public RoomSearchDTO() {
        this.roomType = null;
        this.start = null;
        this.end = null;
    }
}
