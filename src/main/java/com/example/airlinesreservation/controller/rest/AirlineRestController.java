package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.Airline;
import com.example.airlinesreservation.service.AirlineService;
import com.example.airlinesreservation.validation.AirlineValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class AirlineRestController {

    @Autowired
    AirlineService airlineService;

    @GetMapping("getAllAirlines")
    public ResponseEntity<List<Airline>> getAllAirlines(){
        List<Airline> retrievedAirline = airlineService.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(retrievedAirline);
    }

    @GetMapping("getAirlineById")
    public ResponseEntity<Airline> getAirlineById(@RequestParam Long airlineId){
        Airline retrievedAirline = airlineService.findById(airlineId);

        if(retrievedAirline == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedAirline);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedAirline);
    }

    @PostMapping("saveAirline")
    public ResponseEntity<Airline> saveAirline(@RequestBody Airline airline){
        Airline savedAirline = airlineService.saveAirline(airline);

        if(savedAirline == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(savedAirline);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedAirline);
    }

    @PutMapping("updateAirline")
    public ResponseEntity<Airline> updateAirline(@RequestBody Airline airline){
        Airline updatedAirline = airlineService.saveAirline(airline);

        if(updatedAirline == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAirline);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedAirline);
    }

    @DeleteMapping("deleteAirlineById")
    public ResponseEntity<Airline> deleteAirline(@RequestParam Long airlineId){
        Airline deletedAirline = airlineService.deleteById(airlineId);

        if(deletedAirline == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedAirline);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deletedAirline);
    }

}

