package ru.migmak.planeverything.microservices.tasksservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.tasksservice.domain.TaskStep;

public interface TaskStepRepository extends CrudRepository<TaskStep, Long> {
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
