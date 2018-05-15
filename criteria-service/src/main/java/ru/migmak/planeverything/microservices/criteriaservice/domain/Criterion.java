package ru.migmak.planeverything.microservices.criteriaservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "criteria")
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "criterion_order", nullable = false)
    private Integer order;

    @Column(name = "expected_value", nullable = false)
    private Integer expectedValue;

    @Column(name = "actual_value")
    private Integer actualValue;

    @Column(name = "task_id", nullable = false)
    private Long taskId;
}
