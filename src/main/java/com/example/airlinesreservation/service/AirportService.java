package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirportService {

    public Airport saveAirport(Airport airport);
    public List<Airport> getAll();
    public Airport findById(Long airportId);
    public Airport deleteById(Long airportId);

    public Airport updateAirport(Airport airport);

    public Page<Airport> findAirports(Pageable pageable);

}
