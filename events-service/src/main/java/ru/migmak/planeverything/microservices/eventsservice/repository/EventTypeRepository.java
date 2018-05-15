package ru.migmak.planeverything.microservices.eventsservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.migmak.planeverything.microservices.eventsservice.domain.EventType;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface EventTypeRepository extends CrudRepository<EventType, Long> {
    Optional<EventType> findByCode(String code);
}
