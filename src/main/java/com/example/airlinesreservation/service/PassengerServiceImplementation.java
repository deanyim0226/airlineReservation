package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.PassengerRepository;
import com.example.airlinesreservation.domain.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImplementation implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Passenger savePassenger(Passenger passenger) {

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Long passengerId = (Long) session.save(passenger);
            Passenger savedPassenger = session.get(Passenger.class,passengerId);
            session.getTransaction().commit();

            return savedPassenger;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Passenger> findAll() {
        String hql = "From Passenger";

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            List<Passenger> retrievedList = session.createQuery(hql).list();
            session.getTransaction().commit();

            return retrievedList;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Passenger findById(Long passengerId) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Passenger retrievedPassenger = session.get(Passenger.class,passengerId);
            if(retrievedPassenger == null){
                return null;
            }
            session.getTransaction().commit();

            return retrievedPassenger;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Passenger deleteById(Long passengerId) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Passenger retrievedPassenger = session.get(Passenger.class,passengerId);
            if(retrievedPassenger == null){
                return null;
            }
            session.delete(retrievedPassenger);
            session.getTransaction().commit();
            return retrievedPassenger;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Passenger updatePassenger(Passenger passenger) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            Passenger retrievedPassenger = session.get(Passenger.class,passenger.getPassengerId());
            retrievedPassenger.setAddress(passenger.getAddress());
            retrievedPassenger.setEmail(passenger.getEmail());
            retrievedPassenger.setDOB(passenger.getDOB());
            retrievedPassenger.setGender(passenger.getGender());
            retrievedPassenger.setFirstName(passenger.getFirstName());
            retrievedPassenger.setLastName(passenger.getLastName());
            session.update(retrievedPassenger);
            session.getTransaction().commit();

            return retrievedPassenger;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Passenger> findPassengers(Pageable pageable) {
        return passengerRepository.findAll(pageable);
    }


    /*
    USING JPA

    @Override
    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    @Override
    public void deleteById(Long passengerId) {

        passengerRepository.deleteById(passengerId);
    }

     */
}
