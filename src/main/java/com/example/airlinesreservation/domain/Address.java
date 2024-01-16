package com.example.airlinesreservation.domain;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipcode;
    private String country;

}
