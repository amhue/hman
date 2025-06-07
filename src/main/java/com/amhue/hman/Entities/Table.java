package com.amhue.hman.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity(name = "tables")
public class Table {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToMany
    @JsonManagedReference
    private List<TableBooking> tableBookings;

    private Integer tableNumber;

    public Table() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TableBooking> getTableBookings() {
        return tableBookings;
    }

    public void setTableBookings(List<TableBooking> tableBookings) {
        this.tableBookings = tableBookings;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(id, table.id) && Objects.equals(tableBookings, table.tableBookings) && Objects.equals(tableNumber, table.tableNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tableBookings, tableNumber);
    }

    public Table(Integer id, List<TableBooking> tableBookings, Integer tableNumber) {
        this.id = id;
        this.tableBookings = tableBookings;
        this.tableNumber = tableNumber;
    }
}
