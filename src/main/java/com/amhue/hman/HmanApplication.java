package com.amhue.hman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableScheduling
@Controller
public class HmanApplication {
    public static void main(String[] args) {
        SpringApplication.run(HmanApplication.class, args);
    }
}
