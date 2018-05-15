package ru.migmak.planeverything.microservices.criteriaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableResourceServer
@EnableEurekaClient
public class PlanEverythingCriteriaService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingCriteriaService.class, args);
    }
}
