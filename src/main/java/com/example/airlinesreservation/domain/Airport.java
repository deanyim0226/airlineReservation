package com.example.airlinesreservation.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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


    @OneToMany(fetch = FetchType.EAGER)
    private List<Flight> airportArrivalFlights = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Flight> airportDepartureFlights = new ArrayList<>();

}
