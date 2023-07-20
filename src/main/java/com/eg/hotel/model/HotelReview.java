package com.eg.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HotelReview {
    private String id;
    private int rating;
    private String comment;
}
