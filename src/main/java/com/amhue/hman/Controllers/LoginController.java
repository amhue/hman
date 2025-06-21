package com.amhue.hman.Controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        Authentication auth =
            SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated() &&
            !(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println("hello");
            return "redirect:/";
        }

        return "login";
    }
}
