package com.amhue.hman;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/user")
    public List<User> getUser() {
        return List.of(
                new User(
                        "Aritra",
                        "amhue@gmail.com",
                        "7908857562",
                        true
                ),
                new User(
                        "Ladnom",
                        "amhue@example.com",
                        "2407532424",
                        false
                )
        );
    }
}
