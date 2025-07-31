package com.amhue.hman;

public class ReviewDTO {
    private String review;
    private Integer userID;
    private Integer bookingID;

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public Integer getUserID() { return userID; }

    public void setName(Integer userID) { this.userID = userID; }

    public Integer getBookingID() { return bookingID; }

    public void setBookingID(Integer bookingID) { this.bookingID = bookingID; }

    public ReviewDTO(String review, Integer userID, Integer bookingID) {
        this.review = review;
        this.userID = userID;
        this.bookingID = bookingID;
    }
}
