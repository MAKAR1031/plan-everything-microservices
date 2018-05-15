package ru.migmak.planeverything.microservices.projectsservice.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String name;

    private String description;

    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(nullable = false)
    private boolean opened;

    @Column(name = "account_author_id", nullable = false)
    private Long authorId;
}
