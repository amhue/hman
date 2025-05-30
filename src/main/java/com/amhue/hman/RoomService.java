package com.amhue.hman;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public List<Room> getFreeRooms() {
        return roomRepository.findByOccupiedIs(false);
    }

    public void freeRoom(Integer id) {
        roomRepository.findById(id).ifPresent(room -> {
            room.setOccupied(false);
        });
    }
}
