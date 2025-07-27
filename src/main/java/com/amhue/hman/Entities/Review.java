package com.amhue.hman.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id @GeneratedValue private Integer id;
    private String review;
    @ManyToOne private Users user;
    @ManyToOne private Booking booking;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }
    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((review == null) ? 0 : review.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((booking == null) ? 0 : booking.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Review other = (Review)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (review == null) {
            if (other.review != null)
                return false;
        } else if (!review.equals(other.review))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        if (booking == null) {
            if (other.booking != null)
                return false;
        } else if (!booking.equals(other.booking))
            return false;
        return true;
    }

    public Review() {}

    public Review(Integer id, String review, Users user, Booking booking) {
        this.id = id;
        this.review = review;
        this.user = user;
        this.booking = booking;
    }
}
