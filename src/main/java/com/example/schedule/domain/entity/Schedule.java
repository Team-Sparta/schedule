package com.example.schedule.domain.entity;

import com.example.schedule.domain.base.BaseTimeEntity;
import com.example.schedule.domain.enums.Priority;
import com.example.schedule.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Schedules")
public class Schedule extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "text")
    String content;

    @Column(name = "due_date")
    LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM'")
    Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') DEFAULT 'PENDING'")
    Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne(fetch = FetchType.EAGER) // Avoid Lazy loading in immutable records
    @JoinColumn(name = "user_id", nullable = false)
    User user;


    @Builder
    public Schedule(Long id, @NonNull String content,
                    @NonNull LocalDate dueDate,
                    @NonNull Priority priority,
                    @NonNull Status status,
                    @NonNull Category category,
                    @NonNull User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.content = content;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
