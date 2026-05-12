package com.amhue.hman.Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.UsersRepository;
import com.amhue.hman.Services.UsersService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> getUserFromEmail(@RequestParam String email) {
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User with email does not exist!");
        }
        return ResponseEntity.ok().body(user.get());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUserFromId(@PathVariable Integer id) {
        Optional<Users> user = usersRepository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User id is not valid!");
        }
        return ResponseEntity.ok().body(user.get());
    }

    @GetMapping("/auth")
    public ResponseEntity<?>
    getUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return ResponseEntity.badRequest().body(
                "Authentication token is null!");
        }
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found! " + oAuth2User.getAttribute("email"));
        }

        return ResponseEntity.ok().body(user.get());
    }

    @PostMapping("/add")
    public void addUser(@RequestBody Users user) {
        usersService.addUser(user);
    }

    @GetMapping("/current")
    public ResponseEntity<?>
    getCurrentBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found! " + oAuth2User.getAttribute("email"));
        }
        return ResponseEntity.ok().body(
            usersService.getCurrentBookings(user.get()));
    }

    @GetMapping("/upcoming")
    public ResponseEntity<?>
    getUpcomingBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found! " + oAuth2User.getAttribute("email"));
        }
        return ResponseEntity.ok().body(
            usersService.getUpcomingBookings(user.get()));
    }

    @GetMapping("/past")
    public ResponseEntity<?>
    getPastBookings(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found! " + oAuth2User.getAttribute("email"));
        }
        return ResponseEntity.ok().body(
            usersService.getPastBookings(user.get()));
    }

    @GetMapping("/tables")
    public ResponseEntity<?>
    getTables(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found! " + oAuth2User.getAttribute("email"));
        }
        return ResponseEntity.ok().body(usersService.getTables(user.get()));
    }

    @GetMapping("/bills")
    public ResponseEntity<?>
    getBills(@AuthenticationPrincipal OAuth2User oAuth2User) {
        Optional<Users> user =
            usersRepository.findByEmail(oAuth2User.getAttribute("email"));
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(
                "User not found!" + oAuth2User.getAttribute("email"));
        }
        return ResponseEntity.ok().body(usersService.getBills(user.get()));
    }

    @PostMapping("/profile")
    public ResponseEntity<?>
    updateProfile(@RequestParam String name, @RequestParam String phone,
                  @RequestParam(value = "document",
                                required = false) MultipartFile document,
                  @AuthenticationPrincipal OAuth2User oAuth2User) {
        String email = oAuth2User.getAttribute("email");
        Optional<Users> user = usersRepository.findByEmail(email);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found!" + email);
        }

        Users userObj = user.get();
        userObj.setName(name);
        userObj.setPhone(phone);

        if (document != null && !document.isEmpty()) {
            if (!document.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Wrong file type");
            }
            try {
                System.out.println(document.getBytes() + " " +
                                   document.getOriginalFilename());

                File uploadsDir = new File("uploads");
                if (!uploadsDir.exists()) {
                    uploadsDir.mkdirs();
                }

                File file = new File("uploads/" + userObj.getId() + ".pdf");

                if (file.exists()) {
                    file.delete();
                }

                file.createNewFile();

                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(document.getBytes());
                }

                userObj.setDocName(document.getOriginalFilename());
                userObj.setDocType(document.getContentType());
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body(
                    "Could not upload file");
            }
        }

        usersRepository.save(userObj);
        return ResponseEntity.ok().body("Success");
    }
}
