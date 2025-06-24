package com.amhue.hman.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByRoomAndEndDateGreaterThanAndStartDateLessThan(
        Room room, LocalDate start, LocalDate end);
    Optional<Booking> findById(@NonNull Integer id);
}
