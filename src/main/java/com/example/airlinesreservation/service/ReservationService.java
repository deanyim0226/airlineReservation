package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.Reservation;

import java.util.List;

public interface ReservationService {

    public Reservation saveReservation(Reservation reservation);
    public List<Reservation> getAll();
    public Reservation findById(Long reservationId);
    public Reservation deleteReservation(Long reservationId);

    public Reservation findByPassengerEmail(String email);
}
