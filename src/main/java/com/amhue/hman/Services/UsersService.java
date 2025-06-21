package com.amhue.hman.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amhue.hman.BookingDTO;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;

import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void addUser(Users user) {
        Optional<Users> user_db = usersRepository.findByEmail(user.getEmail());

        if (user_db.isEmpty()) {
            usersRepository.save(user);
        } else {
            throw new IllegalStateException("User already present!");
        }
    }

    public List<BookingDTO> getCurrentBookings(Users user) {
        LocalDate today = LocalDate.now();
        return user.getBookings()
            .stream()
            .filter(booking -> booking.getEndDate().isAfter(today))
            .map(booking
                 -> new BookingDTO(booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }

    public List<BookingDTO> getUpcomingBookings(Users user) {
        LocalDate today = LocalDate.now();
        return user.getBookings()
            .stream()
            .filter(booking -> booking.getStartDate().isAfter(today))
            .map(booking
                 -> new BookingDTO(booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }

    public List<BookingDTO> getPastBookings(Users user) {
        LocalDate today = LocalDate.now();
        return user.getBookings()
            .stream()
            .filter(booking -> booking.getEndDate().isBefore(today))
            .map(booking
                 -> new BookingDTO(booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }
}
