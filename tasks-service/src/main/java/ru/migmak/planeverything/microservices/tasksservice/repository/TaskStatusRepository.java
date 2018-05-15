package ru.migmak.planeverything.microservices.tasksservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.tasksservice.domain.TaskStatus;

import java.util.Optional;

public interface TaskStatusRepository extends CrudRepository<TaskStatus, Long> {
    Optional<TaskStatus> findByCode(String code);

    @Override
    @RestResource(exported = false)
    <S extends TaskStatus> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(TaskStatus entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
