package com.example.restaurant.repository;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.enums.CategoryOfDish;

import java.util.List;

public interface IDishRepository extends Repository<Dish,Long>{

    List<Dish> findByCategory(CategoryOfDish category);
}
