package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.AirportRepository;
import com.example.airlinesreservation.domain.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImplementation implements AirportService{

    @Autowired
    AirportRepository airportRepository;

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Long airportId) {
        return airportRepository.findById(airportId).orElse(null);
    }

    @Override
    public void deleteById(Long airportId) {
        airportRepository.deleteById(airportId);
    }
}
