package com.example.restaurant.repository.impl;


import com.example.restaurant.entity.Order;
import com.example.restaurant.service.IOrderService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository implements IOrderService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Order> findAll() {
        return entityManager.unwrap(Session.class)
                .createQuery("from Order order by status DESC, id DESC", Order.class)
                .getResultList();
    }

    @Override
    public Order findById(Long id) {
        return entityManager.unwrap(Session.class).get(Order.class, id);
    }

    @Override
    public void create(Order order) {
        entityManager.unwrap(Session.class).saveOrUpdate(order);
    }

    @Override
    public void delete(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from Order where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }


}
