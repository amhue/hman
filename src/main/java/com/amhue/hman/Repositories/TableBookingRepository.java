package com.amhue.hman.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Entities.TableBooking;
import com.amhue.hman.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TableBookingRepository
    extends JpaRepository<TableBooking, Integer> {
    List<TableBooking>
    findByTableAndEndTimeGreaterThanEqualAndStartTimeLessThanEqual(
        Table table, LocalDateTime start, LocalDateTime end);
    List<TableBooking> findAllByBookingUsers(Users user);
}
