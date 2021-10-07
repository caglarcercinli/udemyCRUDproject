package com.udemycrudproject.component;

import com.udemycrudproject.model.User;
import com.udemycrudproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        User user1 = new User("georginaO", UUID.randomUUID().toString(),"georgina", "ortega", 24, "brazil");
        User user2 = new User( "rosaS", UUID.randomUUID().toString(),"rosa", "sparks", 34, "mexico");
        User user3 = new User( "orlaM", UUID.randomUUID().toString(),"orla", "mccoy", 19, "usa");
        User user4 = new User( "jerryH", UUID.randomUUID().toString(),"jerry", "hanna", 42, "canada");
        User user5 = new User( "serapC", UUID.randomUUID().toString(),"serap", "cercinli", 35, "istasyonalti mah.");

        List<User> usersList = Arrays.asList(user1,user2,user3,user4,user5);
        usersList = usersList.stream().map(user-> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
                    return user;
        }).collect(Collectors.toList());
        userRepository.saveAll(usersList);
    }
}
