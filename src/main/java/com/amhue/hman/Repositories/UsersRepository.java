package com.amhue.hman.Repositories;

import java.util.Optional;

import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.TableBooking;
import com.amhue.hman.Entities.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findById(Integer id);

    Optional<Users> findByBookings_TableBookings(TableBooking tableBooking);

    Optional<Users> findByBookings(Booking booking);
}
