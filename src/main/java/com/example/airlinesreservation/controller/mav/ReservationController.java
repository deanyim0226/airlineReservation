package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.domain.Reservation;
import com.example.airlinesreservation.domain.Search;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.service.PassengerService;
import com.example.airlinesreservation.service.ReservationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    FlightService flightService;

    @Autowired
    PassengerService passengerService;

    @RequestMapping(value = "/saveReservation")
    public ModelAndView saveReservation( @ModelAttribute Reservation reservation, BindingResult br){
        ModelAndView mav = new ModelAndView("searchReservationForm");
        List<Reservation> reservationList = reservationService.getAll();
        reservation.setReservationNumber(Long.valueOf(reservationList.size()+1));

        Flight selectedFlight = flightService.findById(reservation.getFlight().getFlightId());

        System.out.println(selectedFlight);

        List<Passenger> passengerList = passengerService.findAll();

        Passenger passengerInfo = reservation.getPassenger();

        passengerInfo.setPassengerId(Long.valueOf(passengerList.size()+1));
        passengerService.savePassenger(reservation.getPassenger());

        int bookedSeats = selectedFlight.getFlightSeatsBooked();
        selectedFlight.setFlightSeatsBooked(++bookedSeats);
        reservation.setFlight(selectedFlight);
        flightService.updateFlight(selectedFlight);

        if(br.hasErrors()){
            //error while entering reservation info from user
            return mav;
        }

        reservationService.saveReservation(reservation);

        mav.addObject("reservations", reservationService.getAll());
        mav.setViewName("redirect:searchReservationForm");
        return mav;
    }

    @RequestMapping(value = "/updateReservation")
    public ModelAndView updateReservation(Reservation reservation){
        //problem change the way to update from mvc
        ModelAndView mav = new ModelAndView("searchReservationResult");

        Reservation updatedUser = reservationService.updateReservation(reservation);

        if(updatedUser == null){

            //reservation does not exist

            /*
            tasks
            think about the way to update passenger info and flight info

            change format
            XML to JSON
            XML TO XML
            JSON TO XML
             */
            return mav;
        }

        mav.addObject("reservation",updatedUser);
        mav.addObject("reservations",  List.of(updatedUser));
        //mav.addObject("reservations",reservationService.getAll());

        return mav;
    }

    @RequestMapping(value = "/deleteReservation")
    public ModelAndView deleteReservation(@RequestParam Long reservationId){

        ModelAndView mav = new ModelAndView("redirect:searchReservationForm");
        //System.out.println("id is"+reservation.getReservationNumber());
        Reservation retrievedReservation = reservationService.findById(reservationId);

        if(retrievedReservation == null){

            //reservation does not exist
            return mav;
        }
        reservationService.deleteReservation(reservationId);
        mav.addObject("reservations",reservationService.getAll());

        return mav;

    }
}
