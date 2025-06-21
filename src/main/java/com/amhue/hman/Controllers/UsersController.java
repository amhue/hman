package com.amhue.hman.Controllers;

import java.util.List;
import java.util.Optional;

import com.amhue.hman.BookingDTO;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.UsersService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    public UsersController(UsersRepository usersRepository,
                           UsersService usersService) {
        this.usersRepository = usersRepository;
        this.usersService = usersService;
    }

    @GetMapping
    public Users getUserFromEmail(@RequestParam String email) {
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new IllegalStateException("User with email does not exist!");
        } else {
            return user.get();
        }
    }

    @GetMapping("{id}")
    public Users getUserFromId(@PathVariable Integer id) {
        Optional<Users> user = usersRepository.findById(id);

        if (user.isEmpty()) {
            throw new IllegalStateException("User id is not valid!");
        } else {
            return user.get();
        }
    }

    @GetMapping("/auth")
    public Users getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found! " +
                                            oAuth2User.getAttribute("email"));
        }

        return user.get();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody Users user) {
        usersService.addUser(user);
    }

    @GetMapping("/current")
    public List<BookingDTO>
    getCurrentBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found! " +
                                            oAuth2User.getAttribute("email"));
        }
        return usersService.getCurrentBookings(user.get());
    }

    @GetMapping("/upcoming")
    public List<BookingDTO>
    getUpcomingBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found! " +
                                            oAuth2User.getAttribute("email"));
        }
        return usersService.getUpcomingBookings(user.get());
    }

    @GetMapping("/past")
    public List<BookingDTO>
    getPastBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            throw new IllegalStateException("User not found! " +
                                            oAuth2User.getAttribute("email"));
        }
        return usersService.getPastBookings(user.get());
    }
}
