package com.amhue.hman;

public class ReviewDTO {
    private String review;
    private String name;
    private Integer room;

    public String getReview() { return review; }

    public void setReview(String review) { this.review = review; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getRoom() { return room; }

    public void setRoom(Integer room) { this.room = room; }

    public ReviewDTO(String review, String name, Integer room) {
        this.review = review;
        this.name = name;
        this.room = room;
    }
}
