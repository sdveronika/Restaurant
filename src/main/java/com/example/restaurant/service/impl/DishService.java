package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.enums.CategoryOfDish;
import com.example.restaurant.repository.impl.DishRepository;
import com.example.restaurant.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishService implements IDishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    @Transactional
    public List<Dish> findByCategory(CategoryOfDish category) {
        return dishRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    @Override
    @Transactional
    public Dish findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    @Transactional
    public void create(Dish dish) {
        dishRepository.create(dish);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
