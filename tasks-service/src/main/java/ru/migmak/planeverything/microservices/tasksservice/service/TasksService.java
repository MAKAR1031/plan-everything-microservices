package ru.migmak.planeverything.microservices.tasksservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.migmak.planeverything.microservices.tasksservice.client.AccountsClient;
import ru.migmak.planeverything.microservices.tasksservice.client.EventsClient;
import ru.migmak.planeverything.microservices.tasksservice.client.MembersClient;
import ru.migmak.planeverything.microservices.tasksservice.client.model.Account;
import ru.migmak.planeverything.microservices.tasksservice.client.model.ProjectMember;
import ru.migmak.planeverything.microservices.tasksservice.client.model.TaskEvent;
import ru.migmak.planeverything.microservices.tasksservice.domain.Task;
import ru.migmak.planeverything.microservices.tasksservice.domain.TaskStatus;
import ru.migmak.planeverything.microservices.tasksservice.domain.TaskStep;
import ru.migmak.planeverything.microservices.tasksservice.domain.enums.TaskStatusCode;
import ru.migmak.planeverything.microservices.tasksservice.exception.BadRequestException;
import ru.migmak.planeverything.microservices.tasksservice.exception.NotFoundException;
import ru.migmak.planeverything.microservices.tasksservice.exception.ServiceException;
import ru.migmak.planeverything.microservices.tasksservice.repository.TaskRepository;
import ru.migmak.planeverything.microservices.tasksservice.repository.TaskStatusRepository;
import ru.migmak.planeverything.microservices.tasksservice.repository.TaskStepRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.migmak.planeverything.microservices.tasksservice.domain.enums.TaskStatusCode.*;


@Service
@RequiredArgsConstructor
public class TasksService {

    private final TaskRepository taskRepository;
    private final TaskStepRepository taskStepRepository;
    private final TaskStatusRepository statusRepository;
    private final AccountsClient accountsClient;
    private final MembersClient membersClient;
    private final EventsClient eventsClient;

    @Transactional
    public Task assign(Long id, Long memberId) {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new NotFoundException();
        }
        task.setAssigneeId(memberId);
        TaskStatus assignedStatus = getStatus(ASSIGNED);
        task.setStatus(assignedStatus);
        Account currentAccount = accountsClient.findMe();
        TaskEvent event = new TaskEvent("Task assigned", task.getId(), currentAccount.getId());
        eventsClient.update(event);
        return taskRepository.save(task);
    }

    @Transactional
    public Task startTask(Long id) {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new NotFoundException();
        }
        Account currentAccount = accountsClient.findMe();
        TaskStatus status = getStatus(TaskStatusCode.IN_PROGRESS);
        task.setStatus(status);
        TaskEvent event = new TaskEvent("Task execution started", task.getId(), currentAccount.getId());
        eventsClient.update(event);
        return taskRepository.save(task);
    }

    @Transactional
    public Task estimate(Long id) {
        Task task = taskRepository.findOne(id);
        if (task == null) {
            throw new NotFoundException();
        }
        if (!task.getStatus().isFulfilled()) {
            throw new BadRequestException("Task not fulfilled");
        }
        Account currentAccount = accountsClient.findMe();
        TaskStatus status = getStatus(COMPLETED);
        task.setStatus(status);
        TaskEvent event = new TaskEvent("Task complete", task.getId(), currentAccount.getId());
        eventsClient.finish(event);
        return taskRepository.save(task);
    }

    public TaskStep completeStep(Long id, String report) {
        TaskStep step = taskStepRepository.findOne(id);
        if (step == null) {
            throw new NotFoundException();
        }
        Account currentAccount = accountsClient.findMe();
        Long initiatorId = step.getTask().getAssigneeId();
        if (initiatorId == null) {
            throw new BadRequestException("Task not assigned");
        }
        ProjectMember member = membersClient.findById(initiatorId);
        if (!member.getAccountId().equals(currentAccount.getId())) {
            throw new BadRequestException("Task not assigned to current user");
        }
        TaskEvent event = new TaskEvent("Step complete", step.getTask().getId(), currentAccount.getId());
        eventsClient.update(event);
        List<Long> stepsIds = step.getTask()
                .getSteps()
                .stream()
                .filter(s -> !s.isCompleted())
                .map(TaskStep::getId)
                .collect(Collectors.toList());
        if ((stepsIds.size() == 1) && stepsIds.get(0).equals(id)) {
            TaskStatus status = getStatus(FULFILLED);
            step.getTask().setStatus(status);
            event = new TaskEvent("Task fulfilled", step.getTask().getId(), currentAccount.getId());
            eventsClient.update(event);
        }
        step.setCompleted(true);
        if (step.isNeedReport()) {
            if (report == null || report.isEmpty()) {
                throw new BadRequestException("A report should be submitted for this task");
            }
            step.setReport(report);
        }
        return taskStepRepository.save(step);
    }

    private TaskStatus getStatus(TaskStatusCode statusCode) {
        return statusRepository.findByCode(statusCode.name())
                .orElseThrow(() -> new ServiceException(String.format("Task state %s not found", statusCode.name())));
    }
}
