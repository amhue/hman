package com.amhue.hman.Entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {
    @Id @GeneratedValue private Integer id;

    @ManyToOne @JsonIgnore private Users users;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne @JsonBackReference private Room room;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TableBooking> tableBookings;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bill> bills;

    private Boolean checkinNotified;
    private Boolean checkoutNotified;

    public Booking() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Users getUsers() { return users; }

    public void setUsers(Users users) { this.users = users; }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() { return endDate; }

    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public List<TableBooking> getTableBookings() { return tableBookings; }

    public void setTableBookings(List<TableBooking> tableBookings) {
        this.tableBookings = tableBookings;
    }

    public List<Bill> getBills() { return bills; }

    public void setBills(List<Bill> bills) { this.bills = bills; }

    public Boolean getCheckinNotified() { return checkinNotified; }

    public void setCheckinNotified(Boolean checkinNotified) {
        this.checkinNotified = checkinNotified;
    }

    public Boolean getCheckoutNotified() { return checkoutNotified; }

    public void setCheckoutNotified(Boolean checkoutNotified) {
        this.checkoutNotified = checkoutNotified;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((users == null) ? 0 : users.hashCode());
        result =
            prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + ((room == null) ? 0 : room.hashCode());
        result = prime * result +
                 ((tableBookings == null) ? 0 : tableBookings.hashCode());
        result = prime * result + ((bills == null) ? 0 : bills.hashCode());
        result = prime * result +
                 ((checkinNotified == null) ? 0 : checkinNotified.hashCode());
        result = prime * result +
                 ((checkoutNotified == null) ? 0 : checkoutNotified.hashCode());
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
        Booking other = (Booking)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (users == null) {
            if (other.users != null)
                return false;
        } else if (!users.equals(other.users))
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        if (tableBookings == null) {
            if (other.tableBookings != null)
                return false;
        } else if (!tableBookings.equals(other.tableBookings))
            return false;
        if (bills == null) {
            if (other.bills != null)
                return false;
        } else if (!bills.equals(other.bills))
            return false;
        if (checkinNotified == null) {
            if (other.checkinNotified != null)
                return false;
        } else if (!checkinNotified.equals(other.checkinNotified))
            return false;
        if (checkoutNotified == null) {
            if (other.checkoutNotified != null)
                return false;
        } else if (!checkoutNotified.equals(other.checkoutNotified))
            return false;
        return true;
    }

    public Booking(Integer id, Users users, LocalDate startDate,
                   LocalDate endDate, Room room,
                   List<TableBooking> tableBookings, List<Bill> bills,
                   Boolean checkinNotified, Boolean checkoutNotified) {
        this.id = id;
        this.users = users;
        this.startDate = startDate;
        this.endDate = endDate;
        this.room = room;
        this.tableBookings = tableBookings;
        this.bills = bills;
        this.checkinNotified = checkinNotified;
        this.checkoutNotified = checkoutNotified;
    }
}
