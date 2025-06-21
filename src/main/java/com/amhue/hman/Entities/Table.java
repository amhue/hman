package com.amhue.hman.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "tables")
public class Table {
    @Id @GeneratedValue private Integer id;

    @OneToMany(mappedBy = "table")
    @JsonIgnoreProperties("table")
    @Cascade(CascadeType.ALL)
    private List<TableBooking> tableBookings;

    @Column(unique = true) private Integer tableNumber;
    private Integer amount;
    private Integer capacity;

    public Integer getCapacity() { return capacity; }

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Table() {}

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public List<TableBooking> getTableBookings() { return tableBookings; }

    public void setTableBookings(List<TableBooking> tableBookings) {
        this.tableBookings = tableBookings;
    }

    public Integer getTableNumber() { return tableNumber; }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Table other = (Table)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (tableBookings == null) {
            if (other.tableBookings != null)
                return false;
        } else if (!tableBookings.equals(other.tableBookings))
            return false;
        if (tableNumber == null) {
            if (other.tableNumber != null)
                return false;
        } else if (!tableNumber.equals(other.tableNumber))
            return false;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (capacity == null) {
            if (other.capacity != null)
                return false;
        } else if (!capacity.equals(other.capacity))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result +
                 ((tableBookings == null) ? 0 : tableBookings.hashCode());
        result = prime * result +
                 ((tableNumber == null) ? 0 : tableNumber.hashCode());
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result =
            prime * result + ((capacity == null) ? 0 : capacity.hashCode());
        return result;
    }

    public Table(Integer id, List<TableBooking> tableBookings,
                 Integer tableNumber, Integer amount, Integer capacity) {
        this.id = id;
        this.tableBookings = tableBookings;
        this.tableNumber = tableNumber;
        this.amount = amount;
        this.capacity = capacity;
    }
}
