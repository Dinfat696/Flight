package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Flights where the total time spent on the ground exceeds two hours
 */
public class TwoHoursPlusOnGroundFilter implements FlightFilter{
    private final int hoursOnLand;

    public TwoHoursPlusOnGroundFilter(int hoursOnLand) {
        this.hoursOnLand = hoursOnLand;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            long minutesOnLand = 0;
            List<Segment> segments = flight.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                minutesOnLand += ChronoUnit.MINUTES
                        .between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate());
            }
            if (minutesOnLand <= hoursOnLand * 60L) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

}



