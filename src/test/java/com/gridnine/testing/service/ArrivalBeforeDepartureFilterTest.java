package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalBeforeDepartureFilterTest {
    @Test
    void filter(){
        LocalDateTime Now=LocalDateTime.now();
        Flight flightWithArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(Now, Now.minusHours(2))
                , new Segment(Now.plusHours(6), Now.minusHours(7))));
        Flight flightWithoutArrivalBeforeDeparture = new Flight(Arrays.asList(
                new Segment(Now, Now.plusHours(2))
                , new Segment(Now.plusHours(3), Now.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithArrivalBeforeDeparture, flightWithoutArrivalBeforeDeparture);



        //then
        FlightFilter flightFilter = new ArrivalBeforeDepartureFilter();
        List<Flight> flightsResult = flightFilter.filter(flights);
        List<Flight> flightsExpected = List.of(flightWithoutArrivalBeforeDeparture);
        //when
        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}