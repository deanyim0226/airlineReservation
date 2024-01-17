package com.example.airlinesreservation.service;

import com.example.airlinesreservation.domain.User;

import java.util.List;

public interface UserService {

    /*
    add CRUD methods to retrieve, store, update, and delete data
     */

    public List<User> findAll();

    public User findById(Long userId);

    public User saveUser(User user);

    public void deleteById(Long userId);

    public User findByUsername(String username);
}
