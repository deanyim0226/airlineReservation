package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    /*
    add CRUD methods to retrieve, store, update, and delete data
     */

    public List<User> findAll();

    public User findById(Long userId);

    public void registerUser(User user);

    public User saveUser(User user);

    public User deleteById(Long userId);

    public User updateUser(User user);

    public List<User> findByUsername(String username);


    public Page<User> findUsers(Pageable pageable);
}
