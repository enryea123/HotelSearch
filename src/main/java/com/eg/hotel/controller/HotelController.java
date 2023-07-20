package com.eg.hotel.controller;

import com.eg.hotel.database.HotelDatabase;
import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.SearchQuery;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class HotelController {
    private final HotelDatabase hotelDatabase;

    public HotelController() {
        this.hotelDatabase = new HotelDatabase();
    }

    @GetMapping("/hotels")
    public List<Hotel> getHotels(@ModelAttribute SearchQuery query) {
        List<Hotel> allHotels = hotelDatabase.getAllHotels();
        List<Hotel> filteredHotels = filterHotels(allHotels, query);
        return sortHotels(filteredHotels);
    }

    private List<Hotel> filterHotels(List<Hotel> hotels, SearchQuery query) {
        return hotels.stream()
                .filter(hotel -> hotel.getLocation().getName().equalsIgnoreCase(query.getLocation()))
                .filter(hotel -> hotel.getPrice() >= query.getPriceRange().get(0)
                        && hotel.getPrice() <= query.getPriceRange().get(1))
                .filter(hotel -> !hotel.isBooked(query.getCheckinDate(), query.getCheckoutDate()))
                .collect(Collectors.toList());
    }

    private List<Hotel> sortHotels(List<Hotel> hotels) {
        hotels.sort(Comparator.comparing(Hotel::getAverageReview).reversed());
        return hotels;
    }
}
