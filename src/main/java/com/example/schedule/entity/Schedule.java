package com.example.schedule.entity;

import com.example.schedule.enums.Priority;
import com.example.schedule.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "Schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, columnDefinition = "text")
    String content;

    @ManyToOne(fetch = FetchType.EAGER) // Avoid Lazy loading in immutable records
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @Column(name = "due_date")
    LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM'")
    Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') DEFAULT 'PENDING'")
    Status status;

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

}
