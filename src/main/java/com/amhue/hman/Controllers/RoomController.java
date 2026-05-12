package com.amhue.hman.Controllers;

import java.util.List;
import java.util.Optional;

import com.amhue.hman.DTOs.RoomSearchDTO;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.RoomService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;
    private final UsersRepository usersRepository;

    public RoomController(RoomService roomService,
                          UsersRepository usersRepository) {
        this.roomService = roomService;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/search")
    public ResponseEntity<List<Room>>
    searchRooms(@RequestBody RoomSearchDTO roomSearchDTO) {
        if (roomSearchDTO.getStart() != null &&
            roomSearchDTO.getEnd() != null &&
            roomSearchDTO.getRoomType() != null) {
            return ResponseEntity.ok().body(roomService.searchRooms(
                roomSearchDTO.getRoomType(), roomSearchDTO.getStart(),
                roomSearchDTO.getEnd()));
        } else {
            return ResponseEntity.ok().body(roomService.getRooms());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?>
    addRoom(@AuthenticationPrincipal OAuth2User oAuth2User,
            @RequestBody Room room) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "Could not find user with email: " + email);

        } else if (!userDb.get().isMgmt()) {
            return ResponseEntity.badRequest().body(
                "The user is not management!");
        }

        roomService.addRoom(room);

        return ResponseEntity.ok().body("Success!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?>
    deleteRoom(@AuthenticationPrincipal OAuth2User oAuth2User,
               @RequestParam Integer roomNo) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "Could not find user with email: " + email);

        } else if (!userDb.get().isMgmt()) {
            return ResponseEntity.badRequest().body(
                "The user is not management!");
        }

        try {
            roomService.deleteRoom(roomNo);
            return ResponseEntity.ok().body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
