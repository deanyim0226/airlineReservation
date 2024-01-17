package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.PassengerRepository;
import com.example.airlinesreservation.domain.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassenserServiceImplementation implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    @Override
    public void deleteById(Long passengerId) {

        passengerRepository.deleteById(passengerId);
    }
}
