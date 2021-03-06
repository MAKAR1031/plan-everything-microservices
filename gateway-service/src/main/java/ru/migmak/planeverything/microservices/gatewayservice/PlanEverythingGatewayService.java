package ru.migmak.planeverything.microservices.gatewayservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableOAuth2Client
@EnableResourceServer
public class PlanEverythingGatewayService {
    public static void main(String[] args) {
        SpringApplication.run(PlanEverythingGatewayService.class, args);
    }
}
