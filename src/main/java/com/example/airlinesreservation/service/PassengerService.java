package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Passenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PassengerService {


    public Passenger savePassenger(Passenger passenger);
    public List<Passenger> findAll();
    public Passenger findById(Long passengerId);
    public Passenger deleteById(Long passengerId);

    public Passenger updatePassenger(Passenger passenger);

    public Page<Passenger> findPassengers(Pageable pageable);
    /*
    @Id
    private Long passengerId;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private LocalDate DOB;
    @Embedded
    private Address address;
     */
}
