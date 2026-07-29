package com.amhue.hman;

import java.io.IOException;
import java.util.List;

import com.amhue.hman.Services.JwtService;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
        throws ServletException, IOException {

        String token = null;

        // Get JWT from cookie
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {

                if (cookie.getName().equals("jwt")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && jwtService.isValid(token)) {

            String email = jwtService.extractSubject(token);

            User user = (User)User.withUsername(email)
                            .password("")
                            .authorities(List.of())
                            .build();

            UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(user, null,
                                                        user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
