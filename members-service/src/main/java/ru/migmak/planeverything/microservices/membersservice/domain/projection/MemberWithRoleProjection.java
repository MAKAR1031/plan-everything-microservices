package ru.migmak.planeverything.microservices.membersservice.domain.projection;

import org.springframework.data.rest.core.config.Projection;
import ru.migmak.planeverything.microservices.membersservice.domain.MemberRole;
import ru.migmak.planeverything.microservices.membersservice.domain.ProjectMember;

@Projection(name = "withRole", types = ProjectMember.class)
@SuppressWarnings("unused")
public interface MemberWithRoleProjection {
    Long getId();

    Long getProjectId();

    Long getAccountId();

    MemberRole getRole();
}
