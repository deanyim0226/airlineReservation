package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Airline;

import java.util.List;

public interface AirlineService {

    public Airline saveAirline(Airline airline);
    public List<Airline> getAll();
    public Airline findById(Long airlineId);
    public void deleteById(Long airlineId);
}
