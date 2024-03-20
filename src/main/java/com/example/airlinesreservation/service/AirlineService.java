package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {

    public Airline saveAirline(Airline airline);
    public List<Airline> getAll();
    public Airline findById(Long airlineId);
    public Airline deleteById(Long airlineId);

    public Airline updateAirline(Airline airline);

    public Page<Airline> findAirlines(Pageable pageable);
}
