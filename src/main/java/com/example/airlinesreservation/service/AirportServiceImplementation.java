package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.AirportRepository;
import com.example.airlinesreservation.domain.Airport;
import com.example.airlinesreservation.domain.Role;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportServiceImplementation implements AirportService{

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public Airport saveAirport(Airport airport) {
        Airport savedAirport = null;
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();

            Long airportId = (Long)session.save(airport);
            savedAirport = session.get(Airport.class,airportId);

            session.getTransaction().commit();

            return savedAirport;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Airport> getAll() {
        String hql = "From Airport";
        List<Airport> airportList = null;

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();

            airportList = session.createQuery(hql).getResultList();
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return airportList;
    }

    @Override
    public Airport findById(Long airportId) {
        Airport retrievedAirport = null;

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            retrievedAirport = session.get(Airport.class,airportId);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }

        return retrievedAirport;
    }

    @Override
    public Airport deleteById(Long airportId) {

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();

            Airport retrievedAirport = session.get(Airport.class,airportId);

            if(retrievedAirport == null){
                session.getTransaction().commit();
                return null;
            }
            session.delete(retrievedAirport);
            session.getTransaction().commit();

            return retrievedAirport;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airport updateAirport(Airport airport) {


        String hql = "From Airport where airportId = :param";

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();

            Query query = session.createQuery(hql);
            query.setParameter("param",airport.getAirportId());
            Airport retrievedAirport = (Airport) query.getSingleResult();
            if(retrievedAirport == null){

                session.getTransaction().commit();
                return null;
            }
            retrievedAirport.setAirportCity(airport.getAirportCity());
            retrievedAirport.setAirportCode(airport.getAirportCode());
            retrievedAirport.setAirportName(airport.getAirportName());
            retrievedAirport.setAirportArrivalFlights(airport.getAirportArrivalFlights());
            retrievedAirport.setAirportDepartureFlights(airport.getAirportDepartureFlights());
            session.update(retrievedAirport);

            session.getTransaction().commit();

            return retrievedAirport;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    @Autowired
    AirportRepository airportRepository;

    @Override
    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    @Override
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @Override
    public Airport findById(Long airportId) {
        return airportRepository.findById(airportId).orElse(null);
    }

    @Override
    public void deleteById(Long airportId) {
        airportRepository.deleteById(airportId);
    }

     */
}
