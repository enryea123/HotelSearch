package com.eg.hotel.controller;

import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HotelController {
    @GetMapping("/hotels")
    public List<Hotel> getHotels(@ModelAttribute SearchQuery query) {
        // Returning empty list for now
        return new ArrayList<>();
    }
}
