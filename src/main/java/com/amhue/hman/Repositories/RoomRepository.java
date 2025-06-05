package com.amhue.hman.Repositories;

import com.amhue.hman.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository
        extends JpaRepository<Room, Integer> {
    Optional<Room> findById(Integer id);
    Optional<Room> findByRoomNumber(Integer roomNumber);
}
