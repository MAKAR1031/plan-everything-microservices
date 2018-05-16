package ru.migmak.planeverything.microservices.tasksservice.client.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberRole {
    private String name;
    private String code;
    private List<MemberPrivilege> privileges;
}
