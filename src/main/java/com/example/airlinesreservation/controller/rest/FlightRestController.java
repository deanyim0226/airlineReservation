package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class FlightRestController {

    @Autowired
    FlightService flightService;

    @GetMapping("getAllFlights")
    public ResponseEntity<List<Flight>> getAllFlights(){
        List<Flight> flightList = flightService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(flightList);
    }

    @GetMapping("getFlightById")
    public ResponseEntity<Flight> getById(@RequestParam Long flightId){
        Flight retrievedFlight = flightService.findById(flightId);
        if(retrievedFlight == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedFlight);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedFlight);
    }

    @PostMapping("saveFlight")
    public ResponseEntity<Flight> saveFlight(@RequestBody Flight flight){
        Flight savedFlight = flightService.saveFlight(flight);
        if(savedFlight == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(savedFlight);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedFlight);
    }

    @PutMapping("updateFlight")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight){
        Flight updatedFlight = flightService.updateFlight(flight);
        if(updatedFlight == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedFlight);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedFlight);
    }

    @DeleteMapping("deleteFlight")
    public ResponseEntity<Flight> deleteFlight(@RequestParam Long flightId){
        Flight deletedFlight = flightService.deleteById(flightId);
        if(deletedFlight == null){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedFlight);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deletedFlight);
    }
}
