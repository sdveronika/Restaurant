package com.example.restaurant.service;

import com.example.restaurant.entity.Order;

import java.util.List;

public interface IOrderService{

    List<Order> findAll();

    Order findById(Long id);

    void create(Order order);

    void delete(Long id);

}
