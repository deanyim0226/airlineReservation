package com.example.airlinesreservation.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    private Flight flight;

    private int checkedBags;

    private boolean checkedIn;

}
