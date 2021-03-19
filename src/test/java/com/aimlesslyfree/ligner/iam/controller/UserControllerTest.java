package com.aimlesslyfree.ligner.iam.controller;

import com.aimlesslyfree.ligner.iam.db.User;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserControllerTest {

    @Container
    private MariaDBContainer mariaDB = new MariaDBContainer("mariadb:10.5.9")
            .withDatabaseName("iam")
            .withUsername("root")
            .withPassword("root");

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCreateUser() {
        var user = new User("foo@bar.com", "Foo Bar");

        var createdUser = restTemplate.postForObject("/users", user, User.class);

        assertThat(createdUser).isNotNull();
    }
}
