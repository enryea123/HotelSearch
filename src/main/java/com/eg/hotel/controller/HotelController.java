package com.eg.hotel.controller;

import com.eg.hotel.database.HotelDatabase;
import com.eg.hotel.helpers.HotelHelper;
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
    private final HotelHelper hotelHelper;

    public HotelController() {
        this.hotelDatabase = new HotelDatabase();
        this.hotelHelper = new HotelHelper();
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels(@ModelAttribute SearchQuery query) {
        List<Hotel> allHotels = hotelDatabase.getAllHotels();
        List<Hotel> filteredHotels = hotelHelper.filterHotels(allHotels, query);
        return hotelHelper.sortHotels(filteredHotels);
    }
}
