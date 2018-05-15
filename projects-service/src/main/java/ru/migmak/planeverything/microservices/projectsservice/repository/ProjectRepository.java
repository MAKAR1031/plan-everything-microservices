package ru.migmak.planeverything.microservices.projectsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.projectsservice.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
    @Override
    @RestResource(exported = false)
    Iterable<Project> findAll();

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
