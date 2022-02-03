package com.example.restaurant.repository;

import java.util.List;

public interface Repository<T,K> {

    List<T> findAll();

    T findById(K id);

    void create (T entity);

    void deleteById (K id);
}
