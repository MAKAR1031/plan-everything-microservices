package ru.migmak.planeverything.microservices.authorizationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final TokenStore tokenStore = new InMemoryTokenStore();

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("ui")
            .and()
                .withClient("gateway-service")
                .secret("$2a$10$BPtX1kX45DoXFBT4zqtgLuSM/hqEIIRDL4MmBAg9oBDj5or0m8fpO")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("projects-service")
                .secret("$2a$10$I2zdcYu4CB.1lqhgtjaCYuu7GXrz6eL5Dblhh0lvwNuAMMrsETiXm")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("tasks-service")
                .secret("$2a$10$BgHWIhK46KJckm15uKo5auwm6M43kdBgZJFHljjw9uAdRIEk8MLQq")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("criteria-service")
                .secret("$2a$10$jkYtibcXEEREb54kPPlhrePczFM2n2x9XNvtTUp.OQDCaBDHvVKcC")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("tags-service")
                .secret("$2a$10$pmTotc.K/Q1HNPjXkF/3Y.hhE3nF8Ym0c0ThcxNK4MK.b4I0A9q8m")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("members-service")
                .secret("$2a$10$oou6eicww/hWZW7KejyrJe7MJJyKYrXNtSQOAtDaus1/nZqYie34i")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all")
            .and()
                .withClient("events-service")
                .secret("$2a$10$KetkDHgiW34UWuQ4pGosw.zu/qf/BauJYzxB55UZdWUaPZa7hiGaC")
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("all");
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
