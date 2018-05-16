package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.MemberRole;

import java.util.Optional;

public interface MemberRoleRepository extends CrudRepository<MemberRole, Long> {
    Optional<MemberRole> findByCode(@Param("code") String code);

    @Override
    @RestResource(exported = false)
    <S extends MemberRole> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends MemberRole> Iterable<S> save(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void delete(Long aLong);

    @Override
    @RestResource(exported = false)
    void delete(Iterable<? extends MemberRole> iterable);

    @Override
    @RestResource(exported = false)
    void delete(MemberRole entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
