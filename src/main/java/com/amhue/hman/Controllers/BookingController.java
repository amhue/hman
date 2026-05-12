package com.amhue.hman.Controllers;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.amhue.hman.DTOs.BookingDTO;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.RoomRepository;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.BillService;
import com.amhue.hman.Services.BookingService;
import com.amhue.hman.Services.UsersService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    private final RoomRepository roomRepository;
    private final UsersRepository usersRepository;
    private final BillService billService;
    public final UsersService usersService;

    public BookingController(BookingService bookingService,
                             RoomRepository roomRepository,
                             UsersRepository usersRepository,
                             BillService billService,
                             UsersService usersService) {
        this.bookingService = bookingService;
        this.roomRepository = roomRepository;
        this.usersRepository = usersRepository;
        this.billService = billService;
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<?> bookRoom(@RequestBody BookingDTO bookingDTO) {
        Optional<Room> room =
            roomRepository.findByRoomNumber(bookingDTO.getRoomNo());
        Optional<Users> user = usersRepository.findById(bookingDTO.getUserID());
        if (room.isEmpty()) {
            return ResponseEntity.badRequest().body("Room number not found!");
        } else if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!");
        } else {
            Booking booking = bookingService.bookRoom(user.get(), room.get(),
                                                      bookingDTO.getStart(),
                                                      bookingDTO.getEnd());

            int daysBooked = (int)ChronoUnit.DAYS.between(bookingDTO.getStart(),
                                                          bookingDTO.getEnd());
            Integer roomPrice = room.get().getPrice() * daysBooked;

            billService.addBill(
                "Accommodation cost " +
                    bookingDTO.getStart().atStartOfDay().toLocalDate() + " - " +
                    bookingDTO.getEnd().atStartOfDay().toLocalDate(),
                roomPrice, booking);

            List<Booking> bookings = room.get().getBooking();
            if (bookings == null) {
                bookings = new ArrayList<>();
            }
            bookings.add(booking);
            room.get().setBooking(bookings);
            roomRepository.save(room.get());
        }

        return ResponseEntity.ok().body("Success!");
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok().body(bookingService.getAllBookings());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>
    deleteBooking(@AuthenticationPrincipal OAuth2User oAuth2User,
                  @PathVariable Integer id) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User with email " + email +
                                                    " not found!");
        }
        bookingService.deleteBooking(id);
        return ResponseEntity.ok().body(
            usersService.getUpcomingBookings(user.get()));
    }
}
