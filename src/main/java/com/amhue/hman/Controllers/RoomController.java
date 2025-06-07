package com.amhue.hman.Controllers;

import com.amhue.hman.Entities.Room;
import com.amhue.hman.Services.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomService.getRooms();
    }

    @PostMapping
    public void addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
    }
}
