package com.example.airlinesreservation.service;

import com.example.airlinesreservation.dao.ReservationRepository;
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
        return null;
    }

    @Override
    public Reservation findByPassengerEmail(String email) {
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
