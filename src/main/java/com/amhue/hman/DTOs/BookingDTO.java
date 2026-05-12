package com.amhue.hman.DTOs;

import java.time.LocalDate;

import com.amhue.hman.RoomType;

public class BookingDTO {
    private Integer id;
    private Integer userID;
    private Integer roomNo;
    private LocalDate start;
    private LocalDate end;
    private RoomType roomType;

    public BookingDTO(Integer id, Integer userID, Integer roomNo,
                      LocalDate start, LocalDate end, RoomType roomType) {
        this.id = id;
        this.userID = userID;
        this.roomNo = roomNo;
        this.start = start;
        this.end = end;
        this.roomType = roomType;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getUserID() { return userID; }

    public void setUserID(Integer userID) { this.userID = userID; }

    public Integer getRoomNo() { return roomNo; }

    public void setRoomNo(Integer roomNo) { this.roomNo = roomNo; }

    public LocalDate getStart() { return start; }

    public void setStart(LocalDate start) { this.start = start; }

    public LocalDate getEnd() { return end; }

    public void setEnd(LocalDate end) { this.end = end; }

    public RoomType getRoomType() { return roomType; }

    public void setRoomType(RoomType roomType) { this.roomType = roomType; }
}
