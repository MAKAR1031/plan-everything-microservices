package ru.migmak.planeverything.microservices.criteriaservice.handler;


import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import ru.migmak.planeverything.microservices.criteriaservice.client.TasksClient;
import ru.migmak.planeverything.microservices.criteriaservice.client.model.TaskStatus;
import ru.migmak.planeverything.microservices.criteriaservice.domain.Criterion;
import ru.migmak.planeverything.microservices.criteriaservice.exception.BadRequestException;

@Component
@RepositoryEventHandler
@RequiredArgsConstructor
public class CriterionHandler {

    private final TasksClient tasksClient;

    @HandleBeforeCreate
    @SuppressWarnings("unused")
    public void handleCreate(Criterion criterion) {
        TaskStatus status = tasksClient.getStatus(criterion.getTaskId());
        if (status.isEditable()) {
            throw new BadRequestException("Task can edited only 'CREATED' state");
        }
        if (criterion.getActualValue() != null) {
            throw new BadRequestException("The actual value should not be specified when creating");
        }
    }

    @HandleBeforeSave
    @SuppressWarnings("unused")
    public void handleUpdate(Criterion criterion) {
        TaskStatus status = tasksClient.getStatus(criterion.getTaskId());
        if (status.isFulfilled()) {
            if (criterion.getActualValue() == null) {
                throw new BadRequestException("The actual value should be specified");
            }
        } else {
            if (criterion.getActualValue() != null) {
                throw new BadRequestException("The actual value should not be specified in this state");
            }
        }
    }
}
