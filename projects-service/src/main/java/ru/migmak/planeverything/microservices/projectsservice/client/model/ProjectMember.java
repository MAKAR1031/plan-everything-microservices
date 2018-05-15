package ru.migmak.planeverything.microservices.projectsservice.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMember {
    private Long id;

    private Long projectId;

    private Long accountId;

    private String role;
}
