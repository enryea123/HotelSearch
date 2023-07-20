package com.eg.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Hotel {
    private String id;
    private String name;
    private String description;
    private HotelLocation location;
    private int price;
    private String imageUrl;
    private List<HotelReview> reviews;

    public double getAverageReview() {
        if (reviews == null) {
            return 0;
        }

        int sum = 0;
        for (HotelReview hotelReview : reviews) {
            sum += hotelReview.getRating();
        }

        return (double) sum / reviews.size();
    }

    public boolean isBooked(LocalDate checkinDate, LocalDate checkoutDate) {
        // Logic to check if the hotel is booked for the given date range.
        // For this example, let's assume no hotels are booked for simplicity.
        return false;
    }
}
