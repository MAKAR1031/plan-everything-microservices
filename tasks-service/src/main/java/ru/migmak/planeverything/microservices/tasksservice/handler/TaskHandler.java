package ru.migmak.planeverything.microservices.tasksservice.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;
import ru.migmak.planeverything.microservices.tasksservice.client.AccountsClient;
import ru.migmak.planeverything.microservices.tasksservice.client.EventsClient;
import ru.migmak.planeverything.microservices.tasksservice.client.MembersClient;
import ru.migmak.planeverything.microservices.tasksservice.client.model.Account;
import ru.migmak.planeverything.microservices.tasksservice.client.model.ProjectMember;
import ru.migmak.planeverything.microservices.tasksservice.client.model.TaskEvent;
import ru.migmak.planeverything.microservices.tasksservice.domain.Task;
import ru.migmak.planeverything.microservices.tasksservice.domain.TaskStatus;
import ru.migmak.planeverything.microservices.tasksservice.exception.BadRequestException;
import ru.migmak.planeverything.microservices.tasksservice.exception.ServiceException;
import ru.migmak.planeverything.microservices.tasksservice.repository.TaskStatusRepository;

import javax.transaction.Transactional;

import static ru.migmak.planeverything.microservices.tasksservice.domain.enums.TaskStatusCode.CREATED;

@Component
@Transactional
@RepositoryEventHandler
@RequiredArgsConstructor
public class TaskHandler {

    private final TaskStatusRepository statusRepository;
    private final AccountsClient accountsClient;
    private final MembersClient membersClient;
    private final EventsClient eventsClient;

    @HandleBeforeCreate
    public void handleBeforeCreate(Task task) {
        if (task.getProjectId() == null) {
            return;
        }
        Account account = accountsClient.findMe();
        ProjectMember author = membersClient.findCurrent(task.getProjectId(), account.getId());
        if (!author.hasPrivilege("MANAGE_TASKS")) {
            throw new BadRequestException("You can not manage tasks");
        }
        task.setAuthorId(author.getId());
        TaskStatus createdStatus = statusRepository.findByCode(CREATED.name())
                .orElseThrow(() -> new ServiceException("Status 'CREATE' not found"));
        task.setStatus(createdStatus);
    }

    @HandleAfterCreate
    public void handeAfterCreate(Task task) {
        Account account = accountsClient.findMe();
        TaskEvent event = new TaskEvent("Task created", task.getId(), account.getId());
        eventsClient.create(event);
    }

    @HandleBeforeSave
    public void handleUpdate(Task task) {
        checkEditableStatus(task);
        Account account = accountsClient.findMe();
        TaskEvent event = new TaskEvent("Task updated", task.getId(), account.getId());
        eventsClient.update(event);
    }

    @HandleBeforeDelete
    public void handleDelete(Task task) {
        checkEditableStatus(task);
        Account account = accountsClient.findMe();
        TaskEvent event = new TaskEvent("Task removed", task.getId(), account.getId());
        eventsClient.finish(event);
    }


    private void checkEditableStatus(Task task) {
        if (task.getStatus().isEditable()) {
            throw new BadRequestException("The task can not be edited or deleted in this status");
        }
    }
}
