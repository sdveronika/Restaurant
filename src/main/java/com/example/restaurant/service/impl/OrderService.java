package com.example.restaurant.service.impl;

import com.example.restaurant.entity.Order;
import com.example.restaurant.repository.IOrderRepository;
import com.example.restaurant.repository.impl.OrderRepository;
import com.example.restaurant.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void create(Order order) {
        orderRepository.create(order);

    }

    @Override
    @Transactional
    public void delete(Long id) {
      orderRepository.delete(id);
    }
}
