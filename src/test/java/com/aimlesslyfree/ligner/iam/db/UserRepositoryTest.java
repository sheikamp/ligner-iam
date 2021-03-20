package com.aimlesslyfree.ligner.iam.db;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateUser() {
        var user = new User(UUID.randomUUID().toString(), "foo@bar.com", "Foo Bar");
        userRepository.save(user);

        var users = userRepository.findAll();

        assertThat(users).asList().hasSize(1);
    }
}
