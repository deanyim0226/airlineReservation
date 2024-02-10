package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.FlightRepository;
import com.example.airlinesreservation.domain.Airline;
import com.example.airlinesreservation.domain.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImplementation implements FlightService{

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Flight saveFlight(Flight flight) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Long flightId = (Long) session.save(flight);
            Flight savedFlight = session.get(Flight.class,flightId);
            session.getTransaction().commit();

            return savedFlight;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Flight> getAll() {
        String hql = "From Flight";
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            List<Flight> retrievedFlights = session.createQuery(hql).list();
            session.getTransaction().commit();
            return retrievedFlights;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flight findById(Long flightId) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Flight retrievedFlight = session.get(Flight.class,flightId);
            if(retrievedFlight == null){
                return null;
            }
            session.getTransaction().commit();
            return retrievedFlight;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flight deleteById(Long flightId) {
        try(Session session = sessionFactory.openSession();){

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flight updateFlight(Flight flight) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Flight retrievedFlight = session.get(Flight.class, flight.getFlightId());
            retrievedFlight.setFlightAirline(flight.getFlightAirline());
            retrievedFlight.setFlightCapacity(flight.getFlightCapacity());
            retrievedFlight.setFlightNumber(flight.getFlightNumber());
            retrievedFlight.setFlightPrice(flight.getFlightPrice());
            retrievedFlight.setFlightSeatsBooked(flight.getFlightSeatsBooked());
            retrievedFlight.setArrivalCity(flight.getArrivalCity());
            retrievedFlight.setArrivalDate(flight.getArrivalDate());
            retrievedFlight.setArrivalTime(flight.getArrivalTime());
            retrievedFlight.setDepartureCity(flight.getDepartureCity());
            retrievedFlight.setDepartureDate(flight.getDepartureDate());
            retrievedFlight.setDepartureTime(flight.getDepartureTime());
            session.update(retrievedFlight);
            session.getTransaction().commit();

            return retrievedFlight;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /*
    @Override
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long flightId) {
        return flightRepository.findById(flightId).orElse(null);
    }

    @Override
    public void deleteById(Long flightId) {

        flightRepository.deleteById(flightId);
    }

     */
}
