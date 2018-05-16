package ru.migmak.planeverything.microservices.membersservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.migmak.planeverything.microservices.membersservice.domain.Privilege;

@RestResource(exported = false)
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {

}
