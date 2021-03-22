package com.aimlesslyfree.ligner.iam.controller;

import java.util.Collections;
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
        var user = new User(null, "foo@bar.com", "Foo Bar", Collections.emptyList());

        var createdUser = restTemplate.postForObject("/users", user, User.class);

        assertThat(createdUser).isNotNull();
        assertThat(createdUser).usingRecursiveComparison().ignoringFields("id").isEqualTo(user);
    }

    @Test
    void shouldUpdateUser() {
        var user = new User(null, "foo@bar.com", "Foo Bar", Collections.emptyList());
        var createdUser = restTemplate.postForObject("/users", user, User.class);
        var newEmail = "bar@foo.com";
        createdUser.setEmail(newEmail);

        restTemplate.put("/users", createdUser);
        var updatedUser = restTemplate.getForEntity("/users/" + createdUser.getId(), User.class).getBody();

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getEmail()).isEqualTo(newEmail);
    }
}
