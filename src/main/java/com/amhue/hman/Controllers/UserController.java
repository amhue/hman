package com.amhue.hman.Controllers;

import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
}
