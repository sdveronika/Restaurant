package com.example.restaurant.service.impl;

import com.example.restaurant.entity.User;
import com.example.restaurant.repository.impl.UserRepository;
import com.example.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void create(User user) {
        userRepository.create(user);
    }

    @Override
    @Transactional
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<User> findByEmailAndPassword(User user) {
        return userRepository.findByEmailAndPassword(user);
    }
}
