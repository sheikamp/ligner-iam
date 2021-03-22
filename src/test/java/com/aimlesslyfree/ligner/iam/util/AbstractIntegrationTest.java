package com.aimlesslyfree.ligner.iam.util;

import java.util.Map;
import java.util.stream.Stream;

import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.lifecycle.Startables;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;

/**
 * Base class for integration tests.
 * Starts all required dependencies as docker (test-)containers and updates the application config accordingly.
 */
@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Container
    static MariaDBContainer mariaDB = new MariaDBContainer("mariadb:10.5.9").withDatabaseName("iam");

    @Container
    static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3.7.25-management-alpine");

    private static void startContainers() {
      Startables.deepStart(Stream.of(mariaDB, rabbitMQ)).join();
    }

    private static Map<String, Object> createConnectionConfiguration() {
      return Map.of(
          "spring.datasource.url", mariaDB.getJdbcUrl(),
          "spring.datasource.username", mariaDB.getUsername(),
          "spring.datasource.password", mariaDB.getPassword()
      );
    }


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
      startContainers();

      ConfigurableEnvironment environment = applicationContext.getEnvironment();

      MapPropertySource testcontainers = new MapPropertySource("testcontainers", createConnectionConfiguration());

      environment.getPropertySources().addFirst(testcontainers);
    }
  }
} 