package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.ReservationRepository;
import com.example.airlinesreservation.domain.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImplementation implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Override
    public void deleteReservation(Long reservationId) {

        reservationRepository.deleteById(reservationId);
    }
}
