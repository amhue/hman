package com.amhue.hman.Controllers;

import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UsersRepository usersRepository;
    private final UserService userService;

    public UserController(UsersRepository usersRepository, UserService userService) {
        this.usersRepository = usersRepository;
        this.userService = userService;
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

    @PostMapping("/add")
    public void addUser(@RequestBody Users user) {
        userService.addUser(user);
    }
}
