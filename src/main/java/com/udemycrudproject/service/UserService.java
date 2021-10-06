package com.udemycrudproject.service;

import com.udemycrudproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> findAll();
    public Optional<User> findById(long id);
    public void add(User user);
    public Optional<User> delete(long id);
    public Optional<User> update(User user);
}
