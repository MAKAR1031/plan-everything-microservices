package ru.migmak.planeverything.microservices.tasksservice.client.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private Long id;

    private String login;

    private String email;

    private String fullName;
}
