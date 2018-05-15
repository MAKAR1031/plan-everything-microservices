package ru.migmak.planeverything.microservices.tagsservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "color", nullable = false, length = 6)
    private String color;

    @Column(name = "project_id", nullable = false)
    private Long projectId;
}
