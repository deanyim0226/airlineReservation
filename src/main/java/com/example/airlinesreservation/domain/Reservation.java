package com.example.airlinesreservation.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {


    @Id
    private Long reservationNumber;

    @OneToOne
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flightId")
    private Flight flight;

    private int checkedBags;

    private boolean checkedIn;

}
