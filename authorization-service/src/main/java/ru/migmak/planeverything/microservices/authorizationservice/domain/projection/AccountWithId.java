package ru.migmak.planeverything.microservices.authorizationservice.domain.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.migmak.planeverything.microservices.authorizationservice.domain.Account;
import ru.migmak.planeverything.microservices.authorizationservice.domain.AccountRole;

@Projection(name = "full", types = Account.class)
@SuppressWarnings("unused")
public interface AccountWithId {
    Long getId();

    String getLogin();

    String getEmail();

    String getFullName();

    AccountRole getRole();
}
