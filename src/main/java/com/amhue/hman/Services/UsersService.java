package com.amhue.hman.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.amhue.hman.BookingDTO;
import com.amhue.hman.TableBookingDTO;
import com.amhue.hman.Entities.Bill;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.BillRepository;
import com.amhue.hman.Repositories.TableBookingRepository;
import com.amhue.hman.Repositories.UsersRepository;

import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final TableBookingRepository tableBookingRepository;
    private final BillRepository billRepository;

    public UsersService(UsersRepository usersRepository,
                        TableBookingRepository tableBookingRepository,
                        BillRepository billRepository) {
        this.usersRepository = usersRepository;
        this.tableBookingRepository = tableBookingRepository;
        this.billRepository = billRepository;
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
        LocalDateTime now = LocalDateTime.now();
        return user.getBookings()
            .stream()
            .filter(booking
                    -> booking.getEndDate().atTime(12, 30).isAfter(now) &&
                           booking.getStartDate().atTime(12, 30).isBefore(now))
            .map(booking
                 -> new BookingDTO(booking.getId(), booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }

    public List<BookingDTO> getUpcomingBookings(Users user) {
        LocalDateTime now = LocalDateTime.now();
        return user.getBookings()
            .stream()
            .filter(
                booking -> booking.getStartDate().atTime(12, 30).isAfter(now))
            .map(booking
                 -> new BookingDTO(booking.getId(), booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }

    public List<BookingDTO> getPastBookings(Users user) {
        LocalDateTime now = LocalDateTime.now();
        return user.getBookings()
            .stream()
            .filter(
                booking -> booking.getEndDate().atTime(12, 30).isBefore(now))
            .map(booking
                 -> new BookingDTO(booking.getId(), booking.getUsers().getId(),
                                   booking.getRoom().getRoomNumber(),
                                   booking.getStartDate(),
                                   booking.getEndDate()))
            .collect(Collectors.toList());
    }

    public List<TableBookingDTO> getTables(Users user) {
        return tableBookingRepository.findAllByBookingUsers(user)
            .stream()
            .filter(table
                    -> table.getEndTime().isAfter(
                        LocalDateTime.now().minusHours(5).minusMinutes(30)))
            .map(table
                 -> new TableBookingDTO(table.getStartTime(),
                                        table.getEndTime(), table.getAmount(),
                                        table.getBooking().getId(),
                                        table.getTable().getTableNumber()))
            .collect(Collectors.toList());
    }

    public List<Bill> getBills(Users user) {
        return billRepository.findAllByBookingUsers(user);
    }
}
