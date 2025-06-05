package com.amhue.hman.Controllers;

import com.amhue.hman.BookingDTO;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Services.BookingService;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Repositories.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final RoomRepository roomRepository;

    public BookingController(BookingService bookingService, RoomRepository roomRepository) {
        this.bookingService = bookingService;
        this.roomRepository = roomRepository;
    }

    @PostMapping
    public void bookRoom(@RequestBody BookingDTO bookingDTO) {
        Optional<Room> room = roomRepository.findByRoomNumber(bookingDTO.getRoomNo());
        if (room.isEmpty()) {
            throw new IllegalStateException("Room number not found!");
        } else {
            bookingService.bookRoom(room.get(), bookingDTO.getStart(), bookingDTO.getEnd());
        }
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
