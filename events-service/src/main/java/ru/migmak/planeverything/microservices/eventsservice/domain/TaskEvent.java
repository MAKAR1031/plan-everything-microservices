package ru.migmak.planeverything.microservices.eventsservice.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.migmak.planeverything.microservices.eventsservice.domain.enums.EventTypeCode;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "task_events")
public class TaskEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "event_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    @ManyToOne
    @JoinColumn(name = "event_type_id", nullable = false)
    @JsonIgnore
    private EventType type;

    @Column(name = "task_id", nullable = false)
    private Long taskId;

    @Column(name = "initiator_id", nullable = false)
    private Long initiatorId;

    public TaskEvent(String name, EventType type, Long taskId, Long initiatorId) {
        this.time = new Date();
        this.name = name;
        this.type = type;
        this.taskId = taskId;
        this.initiatorId = initiatorId;
    }

    @SuppressWarnings("unused")
    @JsonIgnore
    public String getEventType() {
        return this.type.getCode();
    }

    public boolean is(EventTypeCode code) {
        return code.name().equals(this.type.getCode());
    }
}
