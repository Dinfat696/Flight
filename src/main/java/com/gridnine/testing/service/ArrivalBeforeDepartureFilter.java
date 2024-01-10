package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 Segments with an arrival date earlier than the departure date
 */

public class ArrivalBeforeDepartureFilter implements FlightFilter{

    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .noneMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate())))
                .collect(Collectors.toList());
    }
}

