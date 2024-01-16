package com.example.airlinesreservation.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    private Long airportId;

    private String airportCode;

    private String airportName;

    private String airportCity;


    @OneToMany
    private List<Flight> airportArrivalFlights = new ArrayList<>();

    @OneToMany
    private List<Flight> airportDepartureFlights = new ArrayList<>();

}
