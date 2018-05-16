package ru.migmak.planeverything.microservices.tasksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableResourceServer
@EnableEurekaClient
@EnableFeignClients
public class PlanEverythingTasksService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingTasksService.class, args);
    }
}
