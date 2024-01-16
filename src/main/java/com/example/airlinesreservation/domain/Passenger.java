package com.example.airlinesreservation.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {

    @Id
    private Long passengerId;

    private String firstName;
    private String lastName;

    private String email;

    private Gender gender;


    private LocalDate DOB;

    @Embedded
    private Address address;



}
