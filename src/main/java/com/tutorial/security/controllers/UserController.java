package com.tutorial.security.controllers;

import com.tutorial.security.models.User;
import com.tutorial.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> getAllEmployees() {
        return repository.findAll();
    }


}
