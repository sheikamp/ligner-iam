package com.aimlesslyfree.ligner.iam.controller;

import java.util.Objects;

import com.aimlesslyfree.ligner.iam.db.User;
import com.aimlesslyfree.ligner.iam.db.UserRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.aimlesslyfree.ligner.iam.config.RabbitMqConfig.USER_CREATE_ROUTING_KEY;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    private final RabbitTemplate rabbitTemplate;

    public UserController(UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        var newUser = userRepository.save(user);
        rabbitTemplate.convertAndSend(USER_CREATE_ROUTING_KEY, "Created user with id " + newUser.getId());
        return newUser;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        Objects.requireNonNull(user.getId(), "Please provide an id for the user you wish to update.");
        return userRepository.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        Objects.requireNonNull(id, "Please provide an id for the user you wish to delete.");
        userRepository.deleteById(id);
    }
}
