package com.amhue.hman.Controllers;

import java.util.List;

import com.amhue.hman.RoomSearchDTO;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Services.RoomService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/search")
    public List<Room> searchRooms(@RequestBody RoomSearchDTO roomSearchDTO) {
        if (roomSearchDTO.getStart() != null &&
            roomSearchDTO.getEnd() != null &&
            roomSearchDTO.getRoomType() != null) {
            return roomService.searchRooms(roomSearchDTO.getRoomType(),
                                           roomSearchDTO.getStart(),
                                           roomSearchDTO.getEnd());
        } else {
            return roomService.getRooms();
        }
    }

    @PostMapping
    public void addRoom(@RequestBody Room room) {
        roomService.addRoom(room);
    }
}
