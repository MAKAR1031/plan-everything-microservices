package ru.migmak.planeverything.microservices.membersservice.domain;

import lombok.Getter;
import lombok.Setter;
import ru.migmak.planeverything.microservices.membersservice.domain.enums.PrivilegeCode;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "members")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "member_role_id", nullable = false)
    private MemberRole role;

    public boolean hasPrivilege(PrivilegeCode code) {
        return role.getPrivileges().stream().anyMatch(privilege -> privilege.getCode().equals(code.name()));
    }
}
