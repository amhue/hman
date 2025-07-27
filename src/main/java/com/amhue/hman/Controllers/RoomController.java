package com.amhue.hman.Controllers;

import java.util.List;
import java.util.Optional;

import com.amhue.hman.RoomSearchDTO;
import com.amhue.hman.Entities.Room;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.RoomService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping("/add")
    public void addRoom(@AuthenticationPrincipal OAuth2User oAuth2User,
                        @RequestBody Room room) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            throw new IllegalStateException("Could not find user with email: " +
                                            email);

        } else if (!userDb.get().isMgmt()) {
            throw new IllegalStateException("The user is not management!");
        }

        roomService.addRoom(room);
    }
}
