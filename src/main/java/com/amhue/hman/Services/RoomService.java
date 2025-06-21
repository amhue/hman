package com.amhue.hman.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.amhue.hman.RoomType;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Repositories.RoomRepository;

import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final BookingService bookingService;

    public RoomService(RoomRepository roomRepository,
                       BookingService bookingService) {
        this.roomRepository = roomRepository;
        this.bookingService = bookingService;
    }

    public List<Room> getRooms() { return roomRepository.findAll(); }

    public void addRoom(Room room) { roomRepository.save(room); }

    public List<Room> searchRooms(RoomType roomType, LocalDate start,
                                  LocalDate end) {
        return roomRepository.findAll()
            .stream()
            .filter(room -> roomType == room.getRoomType())
            .filter(room -> bookingService.isAvailable(room, start, end))
            .collect(Collectors.toList());
    }
}
