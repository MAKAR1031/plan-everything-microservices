package ru.migmak.planeverything.microservices.tasksservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.migmak.planeverything.microservices.tasksservice.client.model.ProjectMember;

@FeignClient(value = "members-service", path = "/members")
public interface MembersClient {

    @GetMapping(path = "/search/current?projection=withRole")
    ProjectMember findCurrent(@RequestParam("projectId") Long projectId, @RequestParam("accountId") Long accountId);
}
