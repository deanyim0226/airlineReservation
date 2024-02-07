package com.example.airlinesreservation.service;


import com.example.airlinesreservation.dao.UserRepository;
import com.example.airlinesreservation.domain.User;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
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
    public User saveUser(User newUser) {
        if(newUser == null){
            return  null;
        }else{
            String encryptedPassword = encoder.encode(newUser.getPassword());
            newUser.setPassword(encryptedPassword);
            try(Session session = sessionFactory.openSession();){

                session.beginTransaction();
                Long userId = (Long) session.save(newUser);
                User savedUser = session.get(User.class, userId);
                session.getTransaction().commit();

                return savedUser;

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }


    @Override
    public User deleteById(Long userId) {
        User retrievedUser = null;
        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            retrievedUser = session.get(User.class, userId);

            if(retrievedUser != null){
                System.out.println(retrievedUser.getUsername() + " is deleted");
                session.delete(retrievedUser);
            }
            session.getTransaction().commit();


        }catch (Exception e){
            e.printStackTrace();
        }

        return retrievedUser;

    }

    @Override
    public User updateUser(User user) {
        User retrievedUser = null;

        try(Session session = sessionFactory.openSession();){

            session.beginTransaction();
            retrievedUser = session.get(User.class,user.getUserId());

            if(retrievedUser == null){
                return null;
            }

            retrievedUser.setRoles(user.getRoles());
            retrievedUser.setEmail(user.getEmail());
            retrievedUser.setMobileNo(user.getMobileNo());
            retrievedUser.setUsername(user.getUsername());

            session.update(retrievedUser);
            session.getTransaction().commit();

            return retrievedUser;

        }catch (Exception e){
            e.printStackTrace();
        }

        return retrievedUser;
    }

    @Override
    public List<User> findByUsername(String username) {

        String sql = "From User where username = :param";

        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter("param",username);
            List<User> users = query.getResultList();

            session.getTransaction().commit();

            return users;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
        //return userRepository.findByUsername(username); using JPA
    }
}
