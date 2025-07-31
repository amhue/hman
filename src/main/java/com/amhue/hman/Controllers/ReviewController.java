package com.amhue.hman.Controllers;

import java.util.List;
import java.util.Optional;

import com.amhue.hman.ReviewDTO;
import com.amhue.hman.Entities.Booking;
import com.amhue.hman.Entities.Review;
import com.amhue.hman.Entities.Users;
import com.amhue.hman.Repositories.BookingRepository;
import com.amhue.hman.Repositories.ReviewRepository;
import com.amhue.hman.Repositories.UsersRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;
    private final BookingRepository bookingRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            UsersRepository usersRepository,
                            BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.usersRepository = usersRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public void addReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = new Review();
        Optional<Users> user = usersRepository.findById(reviewDTO.getUserID());
        if (user.isEmpty()) {
            throw new IllegalStateException(
                "The given user id does not exist!");
        }
        Optional<Booking> booking =
            bookingRepository.findById(reviewDTO.getBookingID());
        if (booking.isEmpty()) {
            throw new IllegalStateException(
                "The given booking does not exist!");
        }

        review.setUser(user.get());
        review.setBooking(booking.get());
        review.setReview(reviewDTO.getReview());

        reviewRepository.save(review);
    }

    @GetMapping("/room")
    public List<ReviewDTO> getReviewsByRoom(@RequestParam Integer roomNo) {
        return reviewRepository.findAllByBooking_Room_RoomNumber(roomNo)
            .stream()
            .map(
                review
                -> new ReviewDTO(review.getReview(), review.getUser().getId(),
                                 review.getBooking().getRoom().getRoomNumber()))
            .toList();
    }

    @GetMapping("/user")
    public List<ReviewDTO> getReviesByUser(@RequestParam Integer userID) {
        return reviewRepository.findAllByUserId(userID)
            .stream()
            .map(
                review
                -> new ReviewDTO(review.getReview(), review.getUser().getId(),
                                 review.getBooking().getRoom().getRoomNumber()))
            .toList();
    }
}
