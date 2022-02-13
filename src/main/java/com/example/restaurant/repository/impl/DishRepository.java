package com.example.restaurant.repository.impl;

import com.example.restaurant.entity.Dishes;
import com.example.restaurant.entity.User;
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
    public List<Dishes> findByCategory(CategoryOfDish category) {
        return entityManager.unwrap(Session.class)
                .createQuery("from Dishes where category=:category", Dishes.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Dishes> findAll() {
        return entityManager.unwrap(Session.class)
                .createQuery("from Dishes", Dishes.class)
                .getResultList();
    }

    @Override
    public Dishes findById(Long id) {
        return entityManager.unwrap(Session.class).get(Dishes.class, id);
    }

    @Override
    public void create(Dishes dish) {
        entityManager.unwrap(Session.class).saveOrUpdate(dish);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from Dishes where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
