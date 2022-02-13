package com.example.restaurant.repository;

import com.example.restaurant.entity.User;

public interface IUserRepository extends Repository<User,Long>{

    String checkEmailAndPassword(User user);


}
