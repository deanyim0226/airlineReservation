package com.example.airlinesreservation.controller.rest;

import com.example.airlinesreservation.domain.Airport;
import com.example.airlinesreservation.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("r")
public class AirportRestController {

    @Autowired
    AirportService airportService;

    @GetMapping("/getAllAirports")
    public ResponseEntity<List<Airport>> getAll(){
        List<Airport> retrievedAirports = airportService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(retrievedAirports);
    }

    @GetMapping("/getAirportById")
    public ResponseEntity<Airport> getAirportById(@RequestParam Long airportId){
        Airport retrievedAirport = airportService.findById(airportId);

        if(retrievedAirport == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(retrievedAirport);
        }

        return ResponseEntity.status(HttpStatus.OK).body(retrievedAirport);
    }

    @PostMapping("/saveAirport")
    public ResponseEntity<Airport> saveAirport(@RequestBody Airport airport){

        Airport savedAirport = airportService.saveAirport(airport);

        if(savedAirport == null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(savedAirport);
        }

        return ResponseEntity.status(HttpStatus.OK).body(savedAirport);
    }

    @PutMapping("/updateAirport")
    public ResponseEntity<Airport> updateAirPort(@RequestBody Airport airport){

        Airport updatedAirport = airportService.updateAirport(airport);

        if(updatedAirport == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAirport);
        }

        return ResponseEntity.status(HttpStatus.OK).body(updatedAirport);
    }

    @DeleteMapping("/deleteAirportById")
    public ResponseEntity<Airport> deleteAirport(@RequestParam Long airportId){
        Airport deletedAirport = airportService.deleteById(airportId);

        if(deletedAirport == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedAirport);
        }

        return ResponseEntity.status(HttpStatus.OK).body(deletedAirport);
    }

}
