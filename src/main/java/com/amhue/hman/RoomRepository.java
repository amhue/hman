package com.amhue.hman;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository
        extends JpaRepository<Room, Integer> {
    List<Room> findByIsOccupied(boolean occupied);
    Optional<Room> findById(Integer id);
}
