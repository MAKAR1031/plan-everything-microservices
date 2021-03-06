package ru.migmak.planeverything.microservices.eventsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableResourceServer
@EnableEurekaClient
public class PlanEverythingEventsService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingEventsService.class, args);
    }
}
