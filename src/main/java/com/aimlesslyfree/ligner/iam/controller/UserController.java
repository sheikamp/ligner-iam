package com.aimlesslyfree.ligner.iam.controller;

import com.aimlesslyfree.ligner.iam.db.User;
import com.aimlesslyfree.ligner.iam.db.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
