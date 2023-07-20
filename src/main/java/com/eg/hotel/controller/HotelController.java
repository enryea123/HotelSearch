package com.eg.hotel.controller;

import com.eg.hotel.database.HotelDatabase;
import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.SearchQuery;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class HotelController {
    private final HotelDatabase hotelDatabase;

    public HotelController() {
        this.hotelDatabase = new HotelDatabase();
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels(@ModelAttribute SearchQuery query) {
        // Returning database entries for now
        return hotelDatabase.getAllHotels();
    }
}
