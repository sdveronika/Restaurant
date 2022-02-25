package com.example.restaurant.service;

import com.example.restaurant.entity.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();

    User findById(Long id);

    void create(User user);

    void delete(long id);

    List<User> findByEmailAndPassword(User user);
}