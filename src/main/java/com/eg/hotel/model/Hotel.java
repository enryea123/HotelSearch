package com.eg.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}
