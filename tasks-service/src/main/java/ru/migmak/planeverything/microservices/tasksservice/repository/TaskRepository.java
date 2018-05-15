package ru.migmak.planeverything.microservices.tasksservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.tasksservice.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
