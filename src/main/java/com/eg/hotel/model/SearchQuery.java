package com.eg.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class SearchQuery {
    private String location;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private List<Integer> priceRange;
}
