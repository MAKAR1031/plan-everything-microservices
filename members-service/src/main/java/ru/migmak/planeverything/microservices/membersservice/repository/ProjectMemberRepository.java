package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.ProjectMember;

import java.util.Optional;

@RepositoryRestResource(path = "members")
public interface ProjectMemberRepository extends CrudRepository<ProjectMember, Long> {

    @RestResource(path = "/current", rel = "current")
    @Query("select m from ProjectMember m where m.projectId = ?1 and m.accountId = ?2")
    Optional<ProjectMember> findCurrent(@Param("projectId") Long projectId, @Param("accountId") Long accountId);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
