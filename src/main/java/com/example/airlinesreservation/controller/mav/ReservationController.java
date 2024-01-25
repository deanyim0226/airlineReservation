package com.example.airlinesreservation.controller.mav;


import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.domain.Passenger;
import com.example.airlinesreservation.domain.Reservation;
import com.example.airlinesreservation.service.FlightService;
import com.example.airlinesreservation.service.PassengerService;
import com.example.airlinesreservation.service.ReservationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @RequestMapping(value = "/reservationForm")
    public ModelAndView reservationForm(Reservation reservation){
        ModelAndView mav = new ModelAndView("reservationForm");


        mav.addObject("reservations", reservationService.getAll());
        return mav;
    }

    @RequestMapping(value = "/saveReservation")
    public ModelAndView saveReservation( @ModelAttribute Reservation reservation, BindingResult br){
        ModelAndView mav = new ModelAndView("reservationForm");
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

        if(br.hasErrors()){
            //error while entering reservation info from user
            return mav;
        }

        reservationService.saveReservation(reservation);

        mav.addObject("reservations", reservationService.getAll());
        mav.setViewName("redirect:reservationForm");
        return mav;
    }

    @RequestMapping(value = "/updateReservation")
    public ModelAndView updateReservation(@RequestParam Long reservationId){

        ModelAndView mav = new ModelAndView("reservationForm");

        Reservation retrievedReservation = reservationService.findById(reservationId);

        if(retrievedReservation == null){

            //reservation does not exist
            return mav;
        }


        mav.addObject("reservation",retrievedReservation);
        mav.addObject("reservations",reservationService.getAll());

        return mav;

    }

    @RequestMapping(value = "/deleteReservation")
    public ModelAndView deleteReservation(@RequestParam Long reservationId){

        ModelAndView mav = new ModelAndView("reservationForm");

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
