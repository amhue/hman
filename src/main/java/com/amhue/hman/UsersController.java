package com.amhue.hman;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/user")
    public List<Users> getUser() {
        return usersService.getUsers();
    }

    @PostMapping
    public void addNewUsers(@RequestBody Users user) {
        usersService.insertUsers(user);
    }
}
