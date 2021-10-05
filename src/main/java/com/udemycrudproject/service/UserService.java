package com.udemycrudproject.service;

import com.udemycrudproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();
    public Optional<User> findById(long id);
}
