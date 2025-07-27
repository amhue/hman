package com.amhue.hman;

public class TableDTO {
    private Integer tableNo;
    private Integer tableCapacity;
    private boolean isOccupied;
    private Integer bookings;
    private Integer amount;

    public Integer getTableNo() { return tableNo; }

    public void setTableNo(Integer tableNo) { this.tableNo = tableNo; }

    public Integer getTableCapacity() { return tableCapacity; }

    public void setTableCapacity(Integer tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public boolean isOccupied() { return isOccupied; }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Integer getBookings() { return bookings; }

    public void setBookings(Integer bookings) { this.bookings = bookings; }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    public TableDTO(Integer tableNo, Integer tableCapacity, boolean isOccupied,
                    Integer bookings, Integer amount) {
        this.tableNo = tableNo;
        this.tableCapacity = tableCapacity;
        this.isOccupied = isOccupied;
        this.bookings = bookings;
        this.amount = amount;
    }
}
