package ru.migmak.planeverything.microservices.eventsservice.ro;

import lombok.Getter;
import lombok.Setter;
import ru.migmak.planeverything.microservices.eventsservice.domain.TaskEvent;

@Getter
@Setter
public class TaskEventRo {
    private String name;
    private Long taskId;
    private Long initiatorId;

    public TaskEvent toDomain() {
        TaskEvent event = new TaskEvent();
        event.setName(this.name);
        event.setTaskId(this.taskId);
        event.setInitiatorId(this.initiatorId);
        return event;
    }
}
