package com.eg.hotel.controller;

import com.eg.hotel.database.HotelDatabase;
import com.eg.hotel.helpers.HotelHelper;
import com.eg.hotel.model.Hotel;
import com.eg.hotel.model.HotelLocation;
import com.eg.hotel.model.HotelReview;
import com.eg.hotel.model.SearchQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class HotelControllerTest {
    @Mock
    private HotelDatabase hotelDatabase;

    private HotelController hotelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelController = new HotelController(hotelDatabase, new HotelHelper());
    }

    @Test
    void testGetHotels() {
        SearchQuery query = new SearchQuery(
                "Madrid",
                LocalDate.parse("2023-07-25"),
                LocalDate.parse("2023-07-27"),
                List.of(100, 200)
        );

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, null),
                new Hotel("B", "Hotel B", "Description B", madrid, 120, null, null)
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // All hotels correspond to the search criteria
        assertEquals(hotels, result);
    }

    @Test
    void testGetHotels_SortedByReview() {
        SearchQuery query = new SearchQuery(
                "Madrid",
                LocalDate.parse("2023-07-25"),
                LocalDate.parse("2023-07-27"),
                List.of(100, 200)
        );

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, List.of(new HotelReview("4", 4, "4"))),
                new Hotel("B", "Hotel B", "Description B", madrid, 100, null, List.of(new HotelReview("1", 1, "1"))),
                new Hotel("C", "Hotel C", "Description C", madrid, 120, null, List.of(new HotelReview("5", 5, "5")))
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // Hotels are sorted by reviews rating
        assertEquals(List.of(hotels.get(2), hotels.get(0), hotels.get(1)), result);
    }

    @Test
    void testGetHotels_PriceFilter() {
        SearchQuery query = new SearchQuery(
                "Madrid",
                LocalDate.parse("2023-07-25"),
                LocalDate.parse("2023-07-27"),
                List.of(100, 100)
        );

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, null),
                new Hotel("B", "Hotel B", "Description B", madrid, 120, null, null)
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // Hotel B is filtered out by price
        assertEquals(List.of(hotels.get(0)), result);
    }

    @Test
    void testGetHotels_LocationFilter() {
        SearchQuery query = new SearchQuery(
                "Madrid",
                LocalDate.parse("2023-07-25"),
                LocalDate.parse("2023-07-27"),
                List.of(100, 200)
        );

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        HotelLocation barcelona = new HotelLocation("2", "Barcelona");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, null),
                new Hotel("B", "Hotel B", "Description B", barcelona, 120, null, null)
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // Hotel B is filtered out by location
        assertEquals(List.of(hotels.get(0)), result);
    }

    @Test
    void testGetHotels_NullRequestParameters() {
        SearchQuery query = new SearchQuery(null, null, null, null);

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        HotelLocation barcelona = new HotelLocation("2", "Barcelona");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, List.of(new HotelReview("4", 4, "4"))),
                new Hotel("B", "Hotel B", "Description B", madrid, 10000, null, List.of(new HotelReview("1", 1, "1"))),
                new Hotel("C", "Hotel C", "Description C", madrid, 120, null, List.of(new HotelReview("5", 5, "5"))),
                new Hotel("B", "Hotel B", "Description B", barcelona, 100, null, List.of(new HotelReview("3", 3, "3")))
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // All hotels are returned
        assertEquals(List.of(hotels.get(2), hotels.get(0), hotels.get(3), hotels.get(1)), result);
    }

    @Test
    void testGetHotels_NoResults() {
        SearchQuery query = new SearchQuery(
                "Atlantis", // Non-existing city
                LocalDate.parse("2023-07-25"),
                LocalDate.parse("2023-07-27"),
                List.of(100, 200)
        );

        HotelLocation madrid = new HotelLocation("1", "Madrid");
        List<Hotel> hotels = Arrays.asList(
                new Hotel("A", "Hotel A", "Description A", madrid, 100, null, null),
                new Hotel("B", "Hotel B", "Description B", madrid, 120, null, null)
        );
        when(hotelDatabase.getAllHotels()).thenReturn(hotels);

        List<Hotel> result = hotelController.getHotels(query);
        // No hotels corresponding in the database
        assertEquals(0, result.size());
    }
}
