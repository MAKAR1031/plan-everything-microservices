package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.MemberRole;

import java.util.Optional;

public interface MemberRoleRepository extends CrudRepository<MemberRole, Long> {
    Optional<MemberRole> findByCode(String code);

    @Override
    @RestResource(exported = false)
    void delete(MemberRole entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
