package ru.migmak.planeverything.microservices.criteriaservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.criteriaservice.domain.Criterion;

public interface CriteriaRepository extends CrudRepository<Criterion, Long> {
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
