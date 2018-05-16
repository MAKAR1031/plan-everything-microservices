package ru.migmak.planeverything.microservices.tasksservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.migmak.planeverything.microservices.tasksservice.client.model.TaskEvent;

@FeignClient(name = "events-service", path = "/taskEvents")
public interface EventsClient {
    @PostMapping("/create")
    void create(@RequestBody TaskEvent event);

    @PostMapping("/update")
    void update(@RequestBody TaskEvent event);

    @PostMapping("/finish")
    void finish(@RequestBody TaskEvent event);
}
