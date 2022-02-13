package com.example.restaurant.service;

import com.example.restaurant.entity.Dishes;
import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.CategoryOfDish;

import java.util.List;

public interface IDishService {

    List<Dishes> findAll();

    Dishes findById(Long id);

    void create(Dishes dish);

    void delete(Long id);

    List<Dishes> findByCategory(CategoryOfDish category);

}
