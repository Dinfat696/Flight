package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;
/**
 * Bean that represents a flight.
 */
public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}

