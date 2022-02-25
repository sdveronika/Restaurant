package com.example.restaurant.service;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.enums.CategoryOfDish;

import java.util.List;

public interface IDishService {

    List<Dish> findAll();

    Dish findById(Long id);

    void create(Dish dish);

    void delete(Long id);

    List<Dish> findByCategory(CategoryOfDish category);

}
