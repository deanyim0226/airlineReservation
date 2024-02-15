package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.Reservation;
import com.example.airlinesreservation.service.ReservationService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class ReservationRestController {


    @Autowired
    ReservationService reservationService;


    @GetMapping("getAllReservations")
    public ResponseEntity<List<Reservation>> getAll(){

        List<Reservation> retrievedReservations = reservationService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(retrievedReservations);
    }

    @GetMapping("getReservationById")
    public ResponseEntity<Reservation> getById(@RequestParam Long reservationId){

        Reservation retrievedReservation = reservationService.findById(reservationId);

        if(retrievedReservation == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedReservation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedReservation);
    }

    @GetMapping("getReservationByEmail")
    public ResponseEntity<Reservation> getByEmail(@RequestParam String email){

        Reservation retrievedReservation = reservationService.findByPassengerEmail(email);

        if(retrievedReservation == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedReservation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedReservation);
    }

    @PostMapping("saveReservation")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation){

        Reservation savedReservation = reservationService.saveReservation(reservation);

        if(savedReservation == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(savedReservation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedReservation);
    }

    @DeleteMapping ("deleteReservation")
    public ResponseEntity<Reservation> deleteReservation(@RequestParam Long reservationId){

        Reservation deleteReservation = reservationService.deleteReservation(reservationId);

        if(deleteReservation == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(deleteReservation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deleteReservation);
    }

    @PutMapping("updateReservation")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation reservation){

        Reservation updatedReservation = reservationService.updateReservation(reservation);

        if(updatedReservation == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(updatedReservation);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedReservation);
    }

}
