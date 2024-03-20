package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlightService {

    public Flight saveFlight(Flight flight);
    public List<Flight> getAll();
    public Flight findById(Long flightId);
    public Flight deleteById(Long flightId);

    public Flight updateFlight(Flight flight);

    public Page<Flight> findFlights(Pageable pageable);
}
