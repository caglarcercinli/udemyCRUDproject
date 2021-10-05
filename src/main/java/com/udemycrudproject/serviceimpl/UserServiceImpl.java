package com.udemycrudproject.serviceimpl;

import com.udemycrudproject.model.User;
import com.udemycrudproject.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static List<User> usersList = new ArrayList<>();
    private static long COUNTER = 1l;

    static {
        User user = new User(COUNTER++, "georgina", "ortega", 24, "brazil");
        usersList.add(user);
        user = new User(COUNTER++, "rosa", "sparks", 34, "mexico");
        usersList.add(user);
        user = new User(COUNTER++, "orla", "mccoy", 19, "usa");
        usersList.add(user);
        user = new User(COUNTER++, "jerry", "hanna", 42, "canada");
        usersList.add(user);
        user = new User(COUNTER++, "serap", "cercinli", 35, "istasyonalti mah.");
        usersList.add(user);

    }

    @Override
    public List<User> findAll() {
        //List<User> usersList = new ArrayList<>();
        return usersList;
    }

    @Override
    public Optional<User> findById(long id) {
        return usersList.stream().filter(user->user.getId()==id).findFirst();
    }
}
