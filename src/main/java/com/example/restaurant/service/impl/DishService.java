package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Dishes;
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
    public List<Dishes> findByCategory(CategoryOfDish category) {
        return dishRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public List<Dishes> findAll() {
        return dishRepository.findAll();
    }

    @Override
    @Transactional
    public Dishes findById(Long id) {
        return dishRepository.findById(id);
    }

    @Override
    @Transactional
    public void create(Dishes dish) {
        dishRepository.create(dish);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        dishRepository.deleteById(id);
    }
}
