package ru.migmak.planeverything.microservices.tasksservice.client.model;

import lombok.Getter;

@Getter
public class TaskEvent {
    private final String name;
    private final Long taskId;
    private final Long initiatorId;

    public TaskEvent(String name, Long taskId, Long initiatorId) {
        this.name = name;
        this.taskId = taskId;
        this.initiatorId = initiatorId;
    }
}
