package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.ReservationRepository;
import com.example.airlinesreservation.domain.Flight;
import com.example.airlinesreservation.domain.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImplementation implements ReservationService{

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();

            Reservation retrievedReservation = session.get(Reservation.class, reservation.getReservationNumber());
            if(retrievedReservation != null){
                return null;
            }
            Long reservationId = (Long)session.save(reservation);
            Reservation savedReservation = session.get(Reservation.class, reservationId);
            session.getTransaction().commit();
            return savedReservation;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Reservation> getAll() {
        String hql = "From Reservation";
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            List<Reservation> reservationList = session.createQuery(hql).list();
            session.getTransaction().commit();
            return reservationList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation findById(Long reservationId) {

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Reservation retrievedReservation = session.get(Reservation.class,reservationId);
            session.getTransaction().commit();
            return retrievedReservation;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation deleteReservation(Long reservationId) {

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Reservation deletedReservation = session.get(Reservation.class,reservationId);
            if(deletedReservation == null){
                return null;
            }

            Flight retrievedFlight = deletedReservation.getFlight();
            int numberOfBookedSeats = retrievedFlight.getFlightSeatsBooked();

            if(numberOfBookedSeats > 0){
                retrievedFlight.setFlightSeatsBooked(numberOfBookedSeats-1);
                session.update(retrievedFlight);
            }

            session.delete(deletedReservation);
            session.getTransaction().commit();
            return deletedReservation;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            Reservation retrievedReservation = session.get(Reservation.class,reservation.getReservationNumber());

            //maybe need to update flight and passenger in their services
            /*
            retrievedReservation.setFlight(reservation.getFlight());
            retrievedReservation.setPassenger(reservation.getPassenger());
            */
            retrievedReservation.setCheckedIn(reservation.isCheckedIn());
            retrievedReservation.setCheckedBags(reservation.getCheckedBags());

            session.update(retrievedReservation);
            session.getTransaction().commit();

            return retrievedReservation;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reservation findByPassengerEmail(String email) {
        String hql = "From Reservation";
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Reservation> reservationList = session.createQuery(hql).list();
            Reservation matchedReservation = null;

            for(Reservation reservation : reservationList){
                if(email.equals(reservation.getPassenger().getEmail())){
                    matchedReservation = reservation;
                }
            }

            session.getTransaction().commit();
            return  matchedReservation;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /*
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Override
    public void deleteReservation(Long reservationId) {

        reservationRepository.deleteById(reservationId);
    }

     */
}
