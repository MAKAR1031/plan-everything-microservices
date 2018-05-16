package ru.migmak.planeverything.microservices.projectsservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.projectsservice.client.AccountsClient;
import ru.migmak.planeverything.microservices.projectsservice.client.MembersClient;
import ru.migmak.planeverything.microservices.projectsservice.client.model.Account;
import ru.migmak.planeverything.microservices.projectsservice.client.model.ProjectMember;
import ru.migmak.planeverything.microservices.projectsservice.domain.Project;

import java.util.Date;

@RepositoryEventHandler
@Component
@RequiredArgsConstructor
public class ProjectsHandler {

    private static final String DEFAULT_ROLE = "PROJECT_TASK_MANAGER";

    private final AccountsClient accountsClient;
    private final MembersClient membersClient;

    @HandleBeforeCreate
    @Transactional
    public void handleBeforeCreate(Project project) {
        project.setCreateDate(new Date());
        project.setOpened(true);
        Account account = accountsClient.findMe();
        project.setAuthorId(account.getId());
    }

    @HandleAfterCreate
    @Transactional
    public void handleAfterCreate(Project project) {
        Account account = accountsClient.findMe();
        ProjectMember member = new ProjectMember();
        member.setAccountId(account.getId());
        member.setProjectId(project.getId());
        member.setRole(membersClient.findRoleByCode(DEFAULT_ROLE).getId().getHref());
        membersClient.create(member);
    }

}
