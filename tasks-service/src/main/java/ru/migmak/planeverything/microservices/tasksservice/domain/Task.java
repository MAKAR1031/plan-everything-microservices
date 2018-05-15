package ru.migmak.planeverything.microservices.tasksservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String name;

    private String description;

    @Column(name = "expected_complete_date")
    @Temporal(TemporalType.DATE)
    private Date expectedCompleteDate;

    @ManyToOne
    @JoinColumn(name = "task_state_id", nullable = false)
    private TaskStatus status;

    @Column(name = "member_author_id", nullable = false)
    private Long authorId;

    @Column(name = "member_assignee_id")
    private Long assignee;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "task")
    private List<TaskStep> steps;
}
