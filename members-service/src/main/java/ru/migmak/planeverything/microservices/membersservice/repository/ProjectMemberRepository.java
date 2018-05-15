package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.ProjectMember;

@RepositoryRestResource(path = "members")
public interface ProjectMemberRepository extends CrudRepository<ProjectMember, Long> {

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
