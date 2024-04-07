package com.pesto.tech.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TASKS")
@Audited(withModifiedFlag = true)
@AuditTable(schema = "task_audit", value = "TASKS_AUDIT")
@EntityListeners(AuditingEntityListener.class)
public class TaskEntity extends AuditEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private Long taskId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",foreignKey = @ForeignKey(name = "fk_user_tasks_user_id"))
    private UserEntity user;
}
