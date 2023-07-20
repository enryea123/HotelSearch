package com.eg.hotel.helpers;

import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.HotelReview;
import com.eg.hotel.model.SearchQuery;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HotelHelper {
    public List<Hotel> filterHotels(List<Hotel> hotels, SearchQuery query) {
        return hotels.stream()
                .filter(hotel -> hotel.getLocation().getName().equalsIgnoreCase(query.getLocation()))
                .filter(hotel -> hotel.getPrice() >= query.getPriceRange().get(0)
                        && hotel.getPrice() <= query.getPriceRange().get(1))
                .filter(hotel -> !this.isBooked(hotel, query.getCheckinDate(), query.getCheckoutDate()))
                .collect(Collectors.toList());
    }

    public List<Hotel> sortHotels(List<Hotel> hotels) {
        hotels.sort(Comparator.comparing(this::getAverageReview).reversed());
        return hotels;
    }

    private boolean isBooked(Hotel hotel, LocalDate checkinDate, LocalDate checkoutDate) {
        // Logic to check if the hotel is booked for the given date range.
        // For this example, let's assume no hotels are booked for simplicity.
        return false;
    }

    private double getAverageReview(Hotel hotel) {
        List<HotelReview> reviews = hotel.getReviews();
        if (reviews == null) {
            return 0;
        }

        int sum = 0;
        for (HotelReview review : reviews) {
            sum += review.getRating();
        }

        return (double) sum / reviews.size();
    }
}
