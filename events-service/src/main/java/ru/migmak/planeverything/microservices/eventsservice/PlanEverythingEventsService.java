package ru.migmak.planeverything.microservices.eventsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableResourceServer
public class PlanEverythingEventsService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingEventsService.class, args);
    }
}
