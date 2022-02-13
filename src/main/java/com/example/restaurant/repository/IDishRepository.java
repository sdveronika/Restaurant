package com.example.restaurant.repository;

import com.example.restaurant.entity.Dishes;
import com.example.restaurant.entity.enums.CategoryOfDish;

import java.util.List;

public interface IDishRepository extends Repository<Dishes,Long>{

    List<Dishes> findByCategory(CategoryOfDish category);
}
