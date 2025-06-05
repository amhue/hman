package com.amhue.hman.Repositories;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByRoomAndEndDateGreaterThanEqualAndStartDateLessThanEqual(Room room, LocalDate start, LocalDate end);
}
