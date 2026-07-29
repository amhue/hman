package com.amhue.hman;

import java.io.IOException;

import com.amhue.hman.Services.JwtService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2Handler implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final String clientUrl;

    public OAuth2Handler(JwtService jwtService,
                         @org.springframework.beans.factory.annotation.
                         Value("${client.url}") String clientUrl) {
        this.jwtService = jwtService;
        this.clientUrl = clientUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
        throws IOException {

        OAuth2User oauthUser = (OAuth2User)authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");

        String jwt = jwtService.generateToken(email);

        ResponseCookie cookie = ResponseCookie.from("jwt", jwt)
                                    .httpOnly(true)
                                    .path("/")
                                    .maxAge(60 * 60)
                                    .sameSite("None")
                                    .secure(true)
                                    .build();

        // Cookie cookie = new Cookie("jwt", jwt);
        // cookie.setHttpOnly(true);
        // cookie.setPath("/");
        // cookie.setMaxAge(60 * 60);
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        response.sendRedirect(clientUrl + "oauth-success");
    }
}
