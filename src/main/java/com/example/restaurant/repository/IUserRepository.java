package com.example.restaurant.repository;

import com.example.restaurant.entity.User;

import java.util.List;

public interface IUserRepository extends Repository<User,Long>{

    List<User> findByEmailAndPassword(User user);

}
