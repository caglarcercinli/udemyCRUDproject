package com.udemycrudproject.controller;

import com.udemycrudproject.model.User;
import com.udemycrudproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<List<User>>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<User> userOpt = userService.findById(id);

        if (userOpt.isPresent()) {
            return new ResponseEntity<User>(userOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND) ;
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid User user){
        userService.add(user);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        Optional<User> userOpt = userService.delete(id);
        if(userOpt.isPresent()){
            return new ResponseEntity<User>(userOpt.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody User user){
        Optional<User> userOpt = userService.update(user);
        if(userOpt.isPresent()){
            return new ResponseEntity<User>(userOpt.get(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    }
