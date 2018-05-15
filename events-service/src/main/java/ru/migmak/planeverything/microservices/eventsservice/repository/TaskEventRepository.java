package ru.migmak.planeverything.microservices.eventsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.eventsservice.domain.TaskEvent;

public interface TaskEventRepository extends CrudRepository<TaskEvent, Long> {
    @Override
    @RestResource(exported = false)
    <S extends TaskEvent> S save(S entity);

    @Override
    @RestResource(exported = false)
    void delete(TaskEvent entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
