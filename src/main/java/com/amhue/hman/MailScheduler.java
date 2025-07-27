package com.amhue.hman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.TableBooking;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.BookingRepository;
import com.amhue.hman.Repositories.TableBookingRepository;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.MailService;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MailScheduler {
    private final UsersRepository usersRepository;
    private final TableBookingRepository tableBookingRepository;
    private final MailService mailService;
    private final BookingRepository bookingRepository;

    public MailScheduler(UsersRepository usersRepository,
                         TableBookingRepository tableBookingRepository,
                         MailService mailService,
                         BookingRepository bookingRepository) {
        this.usersRepository = usersRepository;
        this.tableBookingRepository = tableBookingRepository;
        this.mailService = mailService;
        this.bookingRepository = bookingRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void sendMail() {
        List<TableBooking> tableBookings =
            tableBookingRepository
                .findAllByStartTimeGreaterThanAndStartTimeLessThanEqual(
                    LocalDateTime.now().plusMinutes(30),
                    LocalDateTime.now().plusMinutes(32));

        List<Booking> startBookings =
            bookingRepository.findAllByStartDateAndCheckinNotifiedIsTrue(
                LocalDate.now());

        List<Booking> endBookings =
            bookingRepository.findAllByEndDateAndCheckoutNotifiedIsTrue(
                LocalDate.now());

        for (var booking : tableBookings) {
            Users user =
                usersRepository.findByBookings_TableBookings(booking).get();
            String subject =
                "Table booking on " +
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                    .format(booking.getStartTime());
            String text = "Dear " + user.getName() +
                          ", you have table reservation of table " +
                          booking.getTable().getTableNumber() +
                          " today at time " +
                          DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                              .format(booking.getStartTime().toLocalTime());

            mailService.sendPlainText(user.getEmail(), subject, text);
        }

        for (var start : startBookings) {
            Users user = usersRepository.findByBookings(start).get();
            String subject = "Check-in today at 12:30 p.m.";
            String text = "Dear " + user.getName() + ", you have room no. " +
                          start.getRoom().getRoomNumber() +
                          " booked today at 12:30 p.m.";
            mailService.sendPlainText(user.getEmail(), subject, text);
        }

        for (var end : endBookings) {
            Users user = usersRepository.findByBookings(end).get();
            String subject = "Check-out today at 12:30 p.m.";
            String text = "Dear, " + user.getName() +
                          ", you have to check out of room no. " +
                          end.getRoom().getRoomNumber() +
                          " today at 12:30 p.m.";
            mailService.sendPlainText(user.getEmail(), subject, text);
        }
    }
}
