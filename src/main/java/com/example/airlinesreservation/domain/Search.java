package com.example.airlinesreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Search {

    private String from;
    private String to;
    private LocalDate date;

    private Long reservationNumber;
    private String passengerEmail;
}
