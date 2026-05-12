package com.amhue.hman.Controllers;

import java.util.Optional;

import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.BillService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;
    private final UsersRepository usersRepository;

    public BillController(BillService billService,
                          UsersRepository usersRepository) {
        this.billService = billService;
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public ResponseEntity<?>
    getBills(@AuthenticationPrincipal OAuth2User oAuth2User,
             @RequestParam(required = false) Integer userId) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> userDb = usersRepository.findByEmail(email);

        if (userDb.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "Could not find user with email: " + email);

        } else if (!userDb.get().isMgmt()) {
            return ResponseEntity.badRequest().body(
                "The user is not management!");
        }

        if (userId == null) {
            return ResponseEntity.ok().body(billService.getBills());
        }

        Optional<Users> user = usersRepository.findById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "Could not find user of id " + userId);
        }
        return ResponseEntity.ok().body(billService.getBills(user.get()));
    }
}
