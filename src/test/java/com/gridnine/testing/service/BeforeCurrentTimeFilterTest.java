package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BeforeCurrentTimeFilterTest {
    @Test
    void filter(){
        LocalDateTime time=LocalDateTime.now();

        Flight flightWithCurrentTime = new Flight(Arrays.asList(
                new Segment(time.minusDays(2), time.plusHours(2))
                , new Segment(time.plusHours(6), time.plusHours(7))));
        Flight flightWithoutCurrentTime = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2))
                , new Segment(time.plusHours(3), time.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithCurrentTime, flightWithoutCurrentTime);


        //then
        FlightFilter flightFilter = new BeforeCurrentTimeFilter(time);
        List<Flight> flightsResult = flightFilter.filter(flights);
        List<Flight> flightsExpected = List.of(flightWithoutCurrentTime);
        //when
        Assertions.assertEquals(flightsResult,flightsExpected);
    }
}
