package com.amhue.hman.Controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    @PostMapping("/api/auth/logout")
    public ResponseEntity<?> logout(HttpServletRequest request,
                                    HttpServletResponse response) {
        var session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Cookie cookie = new Cookie("jwt", "");
        // cookie.setHttpOnly(true);
        // cookie.setPath("/");
        // cookie.setMaxAge(0);

        ResponseCookie jwtCookie = ResponseCookie.from("jwt", "")
                                       .httpOnly(true)
                                       .path("/")
                                       .maxAge(0)
                                       .sameSite("None")
                                       .secure(true)
                                       .build();

        response.addHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        System.out.println("Logged out JWT");

        ResponseCookie jSessionCookie = ResponseCookie.from("JSESSIONID", "")
                                            .httpOnly(true)
                                            .path("/")
                                            .maxAge(0)
                                            .sameSite("None")
                                            .secure(true)
                                            .build();

        response.addHeader(HttpHeaders.SET_COOKIE, jSessionCookie.toString());
        System.out.println("Logged out JSESSION");

        // Cookie jCookie = new Cookie("JSESSIONID", "");
        // jCookie.setHttpOnly(true);
        // jCookie.setPath("/");
        // jCookie.setMaxAge(0);
        // response.addCookie(cookie);
        // System.out.println("Logged out");

        return ResponseEntity.ok("Logged out successfully");
    }
}
