package ru.migmak.planeverything.microservices.tagsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.tagsservice.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
