package com.amhue.hman.Repositories;

import java.util.List;

import com.amhue.hman.Entities.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public List<Review> findAllByBooking_Room_RoomNumber(Integer roomNumber);

    public List<Review> findAllByUserId(Integer userID);
}
