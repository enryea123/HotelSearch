package com.eg.hotel.database;

import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.HotelLocation;
import com.eg.hotel.model.HotelReview;

import java.util.ArrayList;
import java.util.List;

/**
 * This Database is mocked
 */
public class HotelDatabase {
    private final List<Hotel> hotelList;

    public HotelDatabase() {
        HotelReview review1 = new HotelReview("1", 5, "Comment");
        HotelReview review2 = new HotelReview("2", 5, "Comment");
        HotelReview review3 = new HotelReview("3", 4, "Comment");
        HotelReview review4 = new HotelReview("4", 4, "Comment");
        HotelReview review5 = new HotelReview("5", 1, "Comment");

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        HotelLocation barcelona = new HotelLocation("2", "Barcelona");
        HotelLocation valencia = new HotelLocation("2", "Valencia");

        hotelList = new ArrayList<>();
        hotelList.add(new Hotel("A", "Hotel A", "Description A", madrid, 100, "img", List.of(review1, review2)));
        hotelList.add(new Hotel("B", "Hotel B", "Description B", barcelona, 150, "img", List.of(review1, review3)));
        hotelList.add(new Hotel("C", "Hotel C", "Description C", madrid, 200, "img", List.of(review3, review4)));
        hotelList.add(new Hotel("D", "Hotel D", "Description D", madrid, 300, "img", List.of(review1, review2, review3)));
        hotelList.add(new Hotel("E", "Hotel E", "Description E", valencia, 100, "img", List.of(review3)));
        hotelList.add(new Hotel("F", "Hotel F", "Description F", madrid, 120, "img", List.of(review4, review5)));
        hotelList.add(new Hotel("G", "Hotel G", "Description G", barcelona, 150, "img", List.of(review1)));
        hotelList.add(new Hotel("H", "Hotel H", "Description H", madrid, 90, "img", List.of(review5)));
    }

    public List<Hotel> getAllHotels() {
        return hotelList;
    }
}
