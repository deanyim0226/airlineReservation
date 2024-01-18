package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Airport;

import java.util.List;

public interface AirportService {

    public Airport saveAirport(Airport airport);
    public List<Airport> getAll();
    public Airport findById(Long airportId);
    public void deleteById(Long airportId);

}
