package com.amhue.hman.Controllers;

import java.util.List;
import java.util.Optional;

import com.amhue.hman.TableBookingDTO;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Table;
import com.amhue.hman.Entities.TableBooking;
import com.amhue.hman.Repositories.BookingRepository;
import com.amhue.hman.Repositories.TableRepository;
import com.amhue.hman.Services.TableBookingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/table-booking")
public class TableBookingController {
    private final TableBookingService tableBookingService;
    private final BookingRepository bookingRepository;
    private final TableRepository tableRepository;

    public TableBookingController(TableBookingService tableBookingService,
                                  BookingRepository bookingRepository,
                                  TableRepository tableRepository) {
        this.tableBookingService = tableBookingService;
        this.bookingRepository = bookingRepository;
        this.tableRepository = tableRepository;
    }

    @GetMapping
    public List<TableBooking> getTableBookings() {
        return tableBookingService.getTableBookings();
    }

    @PostMapping
    public void addTableBooking(@RequestBody TableBookingDTO tableBookingDTO) {
        Integer bookingId = tableBookingDTO.getBookingID();
        Integer tableNumber = tableBookingDTO.getTableNumber();

        Optional<Booking> booking = bookingRepository.findById(bookingId);
        Optional<Table> table = tableRepository.findByTableNumber(tableNumber);

        if (booking.isEmpty()) {
            throw new IllegalStateException(
                "The given booking ID is not valid!");
        } else if (table.isEmpty()) {
            throw new IllegalStateException(
                "The given Table number was not valid!");
        } else if (tableBookingDTO.getEndTime().isBefore(
                       tableBookingDTO.getStartTime())) {
            throw new IllegalStateException("End time is before start time!");
        } else if (tableBookingDTO.getStartTime().isBefore(
                       booking.get().getStartDate().atTime(6, 59)) ||
                   tableBookingDTO.getEndTime().isAfter(
                       booking.get().getEndDate().atTime(7, 0))) {
            throw new IllegalStateException(
                "The table booking time must fall within the booking date "
                + "range.");
        } else {
            tableBookingService.addTableBooking(
                tableBookingDTO.getStartTime(), tableBookingDTO.getEndTime(),
                table.get().getAmount(), booking.get(), table.get());
        }
    }
}
