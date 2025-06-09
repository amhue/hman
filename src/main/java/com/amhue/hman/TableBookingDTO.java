package com.amhue.hman;

import java.time.LocalDateTime;

public class TableBookingDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer amount;
    private Integer bookingID;
    private Integer tableNumber;

    public TableBookingDTO(LocalDateTime startTime, LocalDateTime endTime, Integer amount, Integer bookingID, Integer tableNumber) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
        this.bookingID = bookingID;
        this.tableNumber = tableNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }
}
