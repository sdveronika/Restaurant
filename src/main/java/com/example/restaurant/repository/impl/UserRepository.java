package com.example.restaurant.repository.impl;

import com.example.restaurant.entity.User;
import com.example.restaurant.entity.enums.Role;
import com.example.restaurant.repository.IUserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserRepository implements IUserRepository {


    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.unwrap(Session.class)
                .createQuery("from User", User.class)
                .getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.unwrap(Session.class).get(User.class, id);
    }

    @Override
    public void create(User user) {
        entityManager.unwrap(Session.class).saveOrUpdate(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.unwrap(Session.class)
                .createQuery("delete from User where id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List<User> findByEmailAndPassword(User user) {
        return entityManager.unwrap(Session.class)
                .createQuery("from User where email=:email", User.class)
                .setParameter("email", user.getEmail())
                .getResultList();
    }


}
