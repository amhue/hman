package com.amhue.hman.Controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.amhue.hman.RoomDTO;
import com.amhue.hman.TableDTO;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.RoomRepository;
import com.amhue.hman.Repositories.TableRepository;
import com.amhue.hman.Repositories.UsersRepository;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UsersRepository usersRepository;
    private final RoomRepository roomRepository;
    private final TableRepository tableRepository;

    public AdminController(UsersRepository usersRepository,
                           RoomRepository roomRepository,
                           TableRepository tableRepository) {
        this.usersRepository = usersRepository;
        this.roomRepository = roomRepository;
        this.tableRepository = tableRepository;
    }

    @GetMapping("/users")
    public List<Users>
    getUsers(@RequestParam(defaultValue = "") String userString,
             @AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            throw new IllegalStateException("Could not find user with email: " +
                                            email);

        } else if (!userDb.get().isMgmt()) {
            throw new IllegalStateException("The user is not management!");
        }

        Pattern pattern = Pattern.compile(userString, Pattern.CASE_INSENSITIVE);

        return usersRepository.findAll()
            .stream()
            .filter(user -> {
                Matcher matcher = pattern.matcher(user.getName());
                return matcher.find();
            })
            .filter(user -> !user.isMgmt())
            .toList();
    }

    @GetMapping("/rooms")
    public List<RoomDTO>
    getRooms(@RequestParam(required = false) Boolean occupied,
             @RequestParam(required = false) String roomNo,
             @AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            throw new IllegalStateException("Could not find user with email: " +
                                            email);

        } else if (!userDb.get().isMgmt()) {
            throw new IllegalStateException("The user is not management!");
        }

        return roomRepository.findAll()
            .stream()
            .map(room
                 -> new RoomDTO(
                     room.getRoomNumber(), room.getRoomType(),
                     room.getBooking().stream().anyMatch(
                         booking
                         -> booking.getEndDate().atTime(12, 30).isAfter(
                                LocalDateTime.now()) &&
                                booking.getStartDate().atTime(12, 30).isBefore(
                                    LocalDateTime.now())),
                     room.getBooking().size()))
            .filter(roomDTO -> {
                if (occupied != null) {
                    return occupied == roomDTO.isOccupied();
                }
                return true;
            })
            .filter(roomDTO -> {
                if (roomNo == null) {
                    return true;
                }
                return roomDTO.getRoomNo().toString().contains(roomNo);
            })
            .sorted((a, b) -> a.getRoomNo().compareTo(b.getRoomNo()))
            .toList();
    }

    @GetMapping("/tables")
    public List<TableDTO>
    getTables(@RequestParam(required = false) String tableNo,
              @RequestParam(required = false) Boolean occupied,
              @AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            throw new IllegalStateException("Could not find user with email: " +
                                            email);

        } else if (!userDb.get().isMgmt()) {
            throw new IllegalStateException("The user is not management!");
        }

        return tableRepository.findAll()
            .stream()
            .map(
                table
                -> new TableDTO(
                    table.getTableNumber(), table.getCapacity(),
                    table.getTableBookings().stream().anyMatch(
                        booking
                        -> booking.getStartTime().toLocalDate().isEqual(
                            LocalDate.now())),
                    table.getTableBookings()
                        .stream()
                        .filter(booking
                                -> booking.getStartTime().toLocalDate().isEqual(
                                    LocalDate.now()))
                        .toList()
                        .size(),
                    table.getAmount()))
            .filter(tableDTO -> {
                if (occupied != null) {
                    return occupied == tableDTO.isOccupied();
                }
                return true;
            })
            .filter(tableDTO -> {
                if (tableNo == null) {
                    return true;
                }
                return tableDTO.getTableNo().toString().contains(tableNo);
            })
            .sorted((a, b) -> a.getTableNo().compareTo(b.getTableNo()))
            .toList();
    }

    @PostMapping("/make-admin")
    public void makeAdmin(@AuthenticationPrincipal OAuth2User oAuth2User,
                          @RequestParam Integer userID) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            throw new IllegalStateException("Could not find user with email: " +
                                            email);

        } else if (!userDb.get().isMgmt()) {
            throw new IllegalStateException("The user is not management!");
        }

        Optional<Users> user = usersRepository.findById(userID);

        if (user.isEmpty()) {
            throw new IllegalStateException("Could not find user!");
        }

        Users userMem = user.get();
        userMem.setMgmt(true);
        usersRepository.save(userMem);
    }
}
