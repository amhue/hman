package com.amhue.hman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class HmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmanApplication.class, args);
    }

    @GetMapping("/")
    public String root(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String name = oAuth2AuthenticationToken.getPrincipal().getAttribute("name");
        System.out.println();
        model.addAttribute("name", "Welcome, " + name);
        return "root";
    }
}
