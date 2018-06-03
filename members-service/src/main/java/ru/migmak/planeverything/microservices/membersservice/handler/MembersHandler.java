package ru.migmak.planeverything.microservices.membersservice.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.membersservice.domain.MemberRole;
import ru.migmak.planeverything.microservices.membersservice.domain.ProjectMember;
import ru.migmak.planeverything.microservices.membersservice.domain.enums.MemberRoleCode;
import ru.migmak.planeverything.microservices.membersservice.exception.ServiceException;
import ru.migmak.planeverything.microservices.membersservice.repository.MemberRoleRepository;

@RepositoryEventHandler
@Component
@Slf4j
@RequiredArgsConstructor
public class MembersHandler {

    private final MemberRoleRepository roleRepository;

    @HandleBeforeCreate
    @Transactional
    public void handleCreate(ProjectMember member) {
        Long projectId = member.getProjectId();
        if (projectId == null) {
            return;
        }
        if (member.getRole() == null) {
            MemberRole defaultRole = roleRepository.findByCode(MemberRoleCode.PROJECT_EXECUTOR.name())
                    .orElseThrow(() -> new ServiceException("Default member role not found"));
            member.setRole(defaultRole);
        }
    }
}
