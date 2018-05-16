package ru.migmak.planeverything.microservices.tasksservice.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMember {
    private Long id;

    private MemberRole role;

    public boolean hasPrivilege(String code) {
        return role.getPrivileges().stream().anyMatch(p -> p.getCode().equals(code));
    }
}
