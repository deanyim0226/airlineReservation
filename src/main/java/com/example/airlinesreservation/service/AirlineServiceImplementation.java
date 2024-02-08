package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.AirlineRepository;
import com.example.airlinesreservation.domain.Airline;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImplementation implements AirlineService{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Airline saveAirline(Airline airline) {



        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();

            Long airlineId = (Long) session.save(airline);
            Airline savedAirline = session.get(Airline.class, airlineId);

            session.getTransaction().commit();
            return savedAirline;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Airline> getAll() {
        List<Airline> retrievedAirlines = null;
        String hql = "From Airline";

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Query query = session.createQuery(hql);
            retrievedAirlines = query.getResultList();
            session.getTransaction().commit();

            return retrievedAirlines;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airline findById(Long airlineId) {

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Airline airline = session.get(Airline.class, airlineId);
            session.getTransaction().commit();

            return airline;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airline deleteById(Long airlineId) {

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Airline retrievedAirline = session.get(Airline.class,airlineId);
            session.delete(retrievedAirline);
            session.getTransaction().commit();
            return retrievedAirline;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airline updateAirline(Airline airline) {

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Airline retrievedAirline = session.get(Airline.class,airline.getAirlineId());

            if(retrievedAirline == null){
                return null;
            }
            retrievedAirline.setAirlineCode(airline.getAirlineCode());
            retrievedAirline.setAirlineFlight(airline.getAirlineFlight());
            retrievedAirline.setAirlineName(airline.getAirlineName());
            session.update(retrievedAirline);
            session.getTransaction().commit();
            return retrievedAirline;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    /*
    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public Airline saveAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    @Override
    public List<Airline> getAll() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline findById(Long airlineId) {
        return airlineRepository.findById(airlineId).orElse(null);
    }

    @Override
    public void deleteById(Long airlineId) {
        airlineRepository.deleteById(airlineId);
    }

     */
}
