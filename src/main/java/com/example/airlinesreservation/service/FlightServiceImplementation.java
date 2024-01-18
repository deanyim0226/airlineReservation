package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.FlightRepository;
import com.example.airlinesreservation.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImplementation implements FlightService{

    @Autowired
    FlightRepository flightRepository;

    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public void deleteById(Long flightId) {

        flightRepository.deleteById(flightId);
    }
}
