package com.amhue.hman.Services;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Repositories.BookingRepository;
import com.amhue.hman.Entities.Room;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        List<Booking> overlap = bookingRepository.findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(room, start, end);
        return overlap.isEmpty();
    }

    public Booking bookRoom(Room room, LocalDate start, LocalDate end) {
        if (!isAvailable(room, start, end)) {
            throw new IllegalArgumentException();
        }

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setStartDate(start);
        booking.setEndDate(end);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
