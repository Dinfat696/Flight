package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoHoursPlusOnGroundFilterTest {
    @Test
    void filter(){
        LocalDateTime time = LocalDateTime.now();
        int hoursOnLand = 2;
        Flight flightWithFiveHoursOnLand = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2))
                , new Segment(time.plusHours(6), time.plusHours(7))));
        Flight flightWithoutFiveHoursOnLand = new Flight(Arrays.asList(
                new Segment(time, time.plusHours(2))
                , new Segment(time.plusHours(1), time.plusHours(7))));

        List<Flight> flights = Arrays.asList(flightWithFiveHoursOnLand, flightWithoutFiveHoursOnLand);

        //then
        FlightFilter flightFilter = new TwoHoursPlusOnGroundFilter(hoursOnLand);
        List<Flight> flightsResult = flightFilter.filter(flights);

        List<Flight> flightsExpected = List.of(flightWithoutFiveHoursOnLand);
        //when
        Assertions.assertEquals(flightsExpected, flightsResult);
    }
}



