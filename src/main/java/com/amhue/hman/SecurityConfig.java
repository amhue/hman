package com.amhue.hman;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
        throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                auth
                -> auth.requestMatchers("/login", "/api/rooms", "/api/booking",
                                        "/api/users", "/api/table",
                                        "/api/table-booking", "/api/users/*",
                                        "/api/rooms/*", "/api/table/*", "/api/admin/*",
                                        "/api/admin")
                       .permitAll()
                       .anyRequest()
                       .authenticated())
            .oauth2Login(oauth
                         -> oauth.loginPage("/login").defaultSuccessUrl(
                             "http://localhost:5173", true))
            .logout(logout
                    -> logout.logoutSuccessUrl("http://localhost:5173/login")
                           .logoutUrl("/logout"));
        System.out.println("Hello");
        return httpSecurity.build();
    }
}
