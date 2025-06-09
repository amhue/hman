package com.amhue.hman.Repositories;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Entities.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TableBookingRepository extends JpaRepository<TableBooking, Integer> {
    List<TableBooking> findByTableAndEndTimeGreaterThanEqualAndStartTimeLessThanEqual(Table table, LocalDateTime start, LocalDateTime end);
}
