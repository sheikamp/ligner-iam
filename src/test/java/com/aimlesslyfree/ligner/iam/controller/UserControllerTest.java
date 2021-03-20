package com.aimlesslyfree.ligner.iam.controller;

import java.util.UUID;

import com.aimlesslyfree.ligner.iam.db.User;
import com.aimlesslyfree.ligner.iam.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends AbstractIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateUser() {
        var user = new User(UUID.randomUUID().toString(), "foo@bar.com", "Foo Bar");

        var createdUser = restTemplate.postForObject("/users", user, User.class);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser).usingRecursiveComparison().isEqualTo(user);
    }
}
