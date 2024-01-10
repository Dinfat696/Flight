package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Departure to the current point in time
 */
public class BeforeCurrentTimeFilter implements FlightFilter {

  private final LocalDateTime time;

    public BeforeCurrentTimeFilter(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime currentTime=LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .limit(1)
                        .anyMatch(segment -> !segment.getDepartureDate().isBefore(time)))
                .collect(Collectors.toList());
    }
}
