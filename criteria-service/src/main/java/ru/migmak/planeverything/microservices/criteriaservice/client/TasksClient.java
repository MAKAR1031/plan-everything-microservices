package ru.migmak.planeverything.microservices.criteriaservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.migmak.planeverything.microservices.criteriaservice.client.model.TaskStatus;

@FeignClient(name = "tasks-service", path = "/tasks")
public interface TasksClient {

    @GetMapping("/{id}/status")
    TaskStatus getStatus(@PathVariable("id") Long id);

}
