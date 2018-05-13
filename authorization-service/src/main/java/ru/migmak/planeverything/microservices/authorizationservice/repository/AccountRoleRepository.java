package ru.migmak.planeverything.microservices.authorizationservice.repository;

import org.springframework.data.repository.CrudRepository;
import ru.migmak.planeverything.microservices.authorizationservice.domain.AccountRole;

import java.util.Optional;

public interface AccountRoleRepository extends CrudRepository<AccountRole, Long> {
    Optional<AccountRole> findByCode(String code);
}
