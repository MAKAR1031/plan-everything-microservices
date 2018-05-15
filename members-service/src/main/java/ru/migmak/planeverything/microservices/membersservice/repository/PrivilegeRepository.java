package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.Privilege;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Privilege> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(Privilege entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
