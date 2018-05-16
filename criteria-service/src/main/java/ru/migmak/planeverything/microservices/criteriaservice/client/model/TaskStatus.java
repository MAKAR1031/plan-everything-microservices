package ru.migmak.planeverything.microservices.criteriaservice.client.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskStatus {
    private String code;

    private enum Code {
        CREATED,
        FULFILLED
    }

    @JsonIgnore
    public boolean isEditable() {
        return !Code.CREATED.name().equals(code);
    }

    @JsonIgnore
    public boolean isFulfilled() {
        return Code.FULFILLED.name().equals(code);
    }
}
