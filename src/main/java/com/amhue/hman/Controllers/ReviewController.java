package com.amhue.hman.Controllers;

import com.amhue.hman.Repositories.ReviewRepository;
import com.amhue.hman.Repositories.UsersRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            UsersRepository usersRepository) {
        this.reviewRepository = reviewRepository;
        this.usersRepository = usersRepository;
    }
}
