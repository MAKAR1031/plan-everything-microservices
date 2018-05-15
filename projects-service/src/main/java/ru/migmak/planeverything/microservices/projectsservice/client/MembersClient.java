package ru.migmak.planeverything.microservices.projectsservice.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.migmak.planeverything.microservices.projectsservice.client.model.ProjectMember;

@FeignClient(name = "members-service")
public interface MembersClient {

    @GetMapping("/members")
    Resources<ProjectMember> getAll();

    @PostMapping("/members")
    Resources<ProjectMember> create(@RequestBody ProjectMember member);

    @GetMapping(path = "memberRoles/search/findByCode")
    Resource<?> findRoleByCode(@RequestParam("code") String code);
}
