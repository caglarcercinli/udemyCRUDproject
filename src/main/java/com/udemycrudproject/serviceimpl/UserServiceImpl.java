package com.udemycrudproject.serviceimpl;

import com.udemycrudproject.model.User;
import com.udemycrudproject.repository.UserRepository;
import com.udemycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.save(user);
    }

    @Override
    public Optional<User> delete(long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if(userOpt.isPresent()) {
            userRepository.delete(userOpt.get());
            return userOpt;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> userOpt = userRepository.findById(user.getId());
        if(userOpt.isPresent()){
            User existingUser = userOpt.get();
            if(user.getUserName()!=null) {
                existingUser.setUserName(user.getUserName());
            }
            if(user.getPassword()!=null) {
                existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if(user.getFirstName()!=null) {
                existingUser.setFirstName(user.getFirstName());
            }
            if(user.getLastName()!=null) {
                existingUser.setLastName(user.getLastName());
            }
            if(user.getAge()>0) {
                existingUser.setAge(user.getAge());
            }
            if(user.getCountry()!=null) {
                existingUser.setCountry(user.getCountry());
            }
            userRepository.save(existingUser);
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }
}
