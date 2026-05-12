package com.amhue.hman.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.amhue.hman.Entities.Table;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.TableService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/table")
public class TableController {
    private final TableService tableService;
    private final UsersRepository usersRepository;

    public TableController(TableService tableService,
                           UsersRepository usersRepository) {
        this.tableService = tableService;
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public ResponseEntity<List<Table>> getTables() {
        return ResponseEntity.ok().body(tableService.getTables());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Table>>
    searchTables(@RequestParam LocalDateTime start,
                 @RequestParam LocalDateTime end,
                 @RequestParam Integer capacity) {
        return ResponseEntity.ok().body(
            tableService.searchTables(start, end, capacity));
    }

    @PostMapping("/add")
    public ResponseEntity<?>
    addTable(@AuthenticationPrincipal OAuth2User oAuth2User,
             @RequestBody Table table) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "Could not find user with email: " + email);

        } else if (!userDb.get().isMgmt()) {
            return ResponseEntity.badRequest().body(
                "The user is not management!");
        }

        tableService.addTable(table);

        return ResponseEntity.ok().body("Success!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?>
    deleteTable(@AuthenticationPrincipal OAuth2User oAuth2User,
                @RequestParam Integer tableNo) {
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
            tableService.deleteTable(tableNo);
            return ResponseEntity.ok().body("Success!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
