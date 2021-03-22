package com.aimlesslyfree.ligner.iam.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String USER_CREATE_ROUTING_KEY = "userEventQueue";

    @Bean
    public Queue userCreateQueue() {
        return new Queue(USER_CREATE_ROUTING_KEY, false);
    }
}
