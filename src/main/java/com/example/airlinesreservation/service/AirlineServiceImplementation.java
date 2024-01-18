package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.AirlineRepository;
import com.example.airlinesreservation.domain.Airline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImplementation implements AirlineService{

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findById(Long airlineId) {
        return airlineRepository.findById(airlineId).orElse(null);
    }

    @Override
    public void deleteById(Long airlineId) {
        airlineRepository.deleteById(airlineId);
    }
}
