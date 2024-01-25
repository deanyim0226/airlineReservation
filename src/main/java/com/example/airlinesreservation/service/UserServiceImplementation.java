package com.example.airlinesreservation.service;


import com.example.airlinesreservation.dao.UserRepository;
import com.example.airlinesreservation.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    SessionFactory sessionFactory;



    @Override
    public List<User> findAll() {
        List<User> users = null;
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            users = session.createQuery("from User").list();
            session.getTransaction().commit();
        }

        return users;
    }

    @Override
    public User findById(Long userId) {
        User user = null;

        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            user = session.get(User.class, userId);

            session.getTransaction().commit();

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return user;
    }

    @Override
    public void registerUser(User user) {

        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        try(Session session = sessionFactory.openSession()){
            //update is not working here it will create a new row
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public User saveUser(User user) {
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }


    @Override
    public void deleteById(Long userId) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            User retrievedUser = session.get(User.class, userId);

            if(retrievedUser != null){
                session.delete(retrievedUser);
            }
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateUser(User user) {
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
