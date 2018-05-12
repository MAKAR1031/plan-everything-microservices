package ru.migmak.planeverything.microservices.discoveryservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PlanEverythingDiscoveryService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingDiscoveryService.class, args);
    }
}
