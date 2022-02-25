package com.example.restaurant.repository.impl;

import com.example.restaurant.entity.Dish;
import com.example.restaurant.entity.enums.CategoryOfDish;
import com.example.restaurant.repository.IDishRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class DishRepository  implements IDishRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Dish> findByCategory(CategoryOfDish category) {
        return entityManager.unwrap(Session.class)
                .createQuery("from Dish where category=:category", Dish.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Dish> findAll() {
        return entityManager.unwrap(Session.class)
                .createQuery("from Dish order by category DESC", Dish.class)
                .getResultList();
    }

    @Override
    public Dish findById(Long id) {
        return entityManager.unwrap(Session.class).get(Dish.class, id);
    }

    @Override
    public void create(Dish dish) {
        entityManager.unwrap(Session.class).saveOrUpdate(dish);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from Dish where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
