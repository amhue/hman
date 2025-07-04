package com.amhue.hman;

import java.time.LocalDate;

public class BookingDTO {
    private Integer id;
    private Integer userID;
    private Integer roomNo;
    private LocalDate start;
    private LocalDate end;

    public BookingDTO(Integer id, Integer userID, Integer roomNo,
                      LocalDate start, LocalDate end) {
        this.id = id;
        this.userID = userID;
        this.roomNo = roomNo;
        this.start = start;
        this.end = end;
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
}
