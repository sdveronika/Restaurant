package com.example.restaurant.repository;

import com.example.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


public interface IOrderRepository extends Repository<Order,Long> {

}
