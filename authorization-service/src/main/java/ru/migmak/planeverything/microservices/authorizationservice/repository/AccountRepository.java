package ru.migmak.planeverything.microservices.authorizationservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.authorizationservice.domain.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findAccountByLogin(String login);

    @RestResource(path = "me", rel = "me")
    @Query("select a from Account a where a.login = :#{principal.username}")
    Optional<Account> findCurrentAccount();
}
