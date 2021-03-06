package ru.migmak.planeverything.microservices.gatewayservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.formLogin().disable()
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/**/oauth/token", "/**/accounts/register")
                .permitAll()
            .and()
            .authorizeRequests()
                .antMatchers("/**")
                .authenticated();
		// @formatter:on
    }
}
