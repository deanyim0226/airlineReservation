package com.example.airlinesreservation.controller.rest;


import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class PassengerRestController {

    @Autowired
    PassengerService passengerService;

    @GetMapping("getAllPassenger")
    ResponseEntity<List<Passenger>> getAll(){

        List<Passenger> passengerList = passengerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(passengerList);
    }
    @GetMapping("getPassengerById")
    ResponseEntity<Passenger> getById(@RequestParam Long passengerId){

        Passenger retrievedPassenger = passengerService.findById(passengerId);

        if(retrievedPassenger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedPassenger);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedPassenger);
    }

    @PostMapping("savePassenger")
    ResponseEntity<Passenger> savePassenger(@RequestBody Passenger passenger){

        Passenger savedPassenger = passengerService.savePassenger(passenger);

        if(savedPassenger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(savedPassenger);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedPassenger);
    }

    @PutMapping("updatePassenger")
    ResponseEntity<Passenger> updatePassenger(@RequestBody Passenger passenger){

        Passenger updatedPassenger = passengerService.updatePassenger(passenger);

        if(updatedPassenger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedPassenger);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedPassenger);
    }

    @DeleteMapping ("deletePassenger")
    ResponseEntity<Passenger> updatePassenger(@RequestParam Long passengerId){

        Passenger deletedPassenger = passengerService.deleteById(passengerId);

        if(deletedPassenger == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedPassenger);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deletedPassenger);
    }
}
