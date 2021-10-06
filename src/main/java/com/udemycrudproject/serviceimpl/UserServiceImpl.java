package com.udemycrudproject.serviceimpl;

import com.udemycrudproject.model.User;
import com.udemycrudproject.repository.UserRepository;
import com.udemycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    public static List<User> usersList = new ArrayList<>();
    private static long COUNTER = 1l;
    @Autowired
    private UserRepository userRepository;

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
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return usersList.stream().filter(user->user.getId()==id).findFirst();
    }

    @Override
    public void add(User user) {
        user.setId(COUNTER++);
        usersList.add(user);
    }

    @Override
    public Optional<User> delete(long id) {
        Optional<User> userOpt = usersList.stream().filter(user ->user.getId()==user.getId()).findFirst();
        if(userOpt.isPresent()) {
            usersList = usersList.stream().filter(u->u.getId()!=userOpt.get().getId()).collect(Collectors.toList());
            //usersList.add(existingUser)
            return userOpt;
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> update(User user) {
        Optional<User> userOpt = usersList.stream().filter(u ->u.getId()==user.getId()).findFirst();
        if(userOpt.isPresent()){
            User existingUser = userOpt.get();

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
            usersList = usersList.stream().filter(u->u.getId()!=existingUser.getId()).collect(Collectors.toList());
            usersList.add(existingUser);
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }
}
