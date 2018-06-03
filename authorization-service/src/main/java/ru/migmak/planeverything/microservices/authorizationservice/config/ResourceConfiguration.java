package ru.migmak.planeverything.microservices.authorizationservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/accounts/register")
                .permitAll()
            .and()
            .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
            .csrf()
                .disable();
        // @formatter:on
    }
}
