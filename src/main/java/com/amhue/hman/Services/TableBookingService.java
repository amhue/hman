package com.amhue.hman.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Table;
import com.amhue.hman.Entities.TableBooking;
import com.amhue.hman.Repositories.TableBookingRepository;

import org.springframework.stereotype.Service;

@Service
public class TableBookingService {
    private final TableBookingRepository tableBookingRepository;
    private final BillService billService;

    public TableBookingService(TableBookingRepository tableBookingRepository,
                               BillService billService) {
        this.tableBookingRepository = tableBookingRepository;
        this.billService = billService;
    }

    public boolean isAvailable(Table table, LocalDateTime start,
                               LocalDateTime end) {
        List<TableBooking> overlap =
            tableBookingRepository
                .findByTableAndEndTimeGreaterThanEqualAndStartTimeLessThanEqual(
                    table, start, end);
        return overlap.isEmpty();
    }

    public List<TableBooking> getTableBookings() {
        return tableBookingRepository.findAll();
    }

    public void addTableBooking(LocalDateTime start, LocalDateTime end,
                                Integer amount, Booking booking, Table table) {
        if (!isAvailable(table, start, end)) {
            throw new IllegalStateException("The table is not available!");
        }

        TableBooking tableBooking = new TableBooking();
        tableBooking.setStartTime(start);
        tableBooking.setEndTime(end);
        tableBooking.setAmount(amount);
        tableBooking.setBooking(booking);
        tableBooking.setTable(table);

        List<TableBooking> tableBookings = booking.getTableBookings();
        if (tableBookings == null) {
            tableBookings = new ArrayList<>();
        }

        tableBookings.add(tableBooking);
        tableBookingRepository.save(tableBooking);

        billService.addBill("Table reservation on " + start.toLocalDate() +
                                " " +
                                start.toLocalTime()
                                    .plusHours(5)
                                    .plusMinutes(30)
                                    .toString()
                                    .substring(0, 5) +
                                ", Table " + table.getTableNumber(),
                            amount, booking);
    }
}
