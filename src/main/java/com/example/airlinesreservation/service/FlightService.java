package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Flight;

import java.util.List;

public interface FlightService {

    public Flight saveFlight(Flight flight);
    public List<Flight> getAll();
    public Flight findById(Long flightId);
    public void deleteById(Long flightId);
}
