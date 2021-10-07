package com.udemycrudproject.component;

import com.udemycrudproject.model.User;
import com.udemycrudproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        User user = new User( "georgina", "ortega", 24, "brazil");
        userRepository.save(user);
        user = new User( "rosa", "sparks", 34, "mexico");
        userRepository.save(user);
        user = new User( "orla", "mccoy", 19, "usa");
        userRepository.save(user);
        user = new User( "jerry", "hanna", 42, "canada");
        userRepository.save(user);
        user = new User( "serap", "cercinli", 35, "istasyonalti mah.");
        userRepository.save(user);
    }
}
