package com.example.schedule.domain.entity;

import com.example.schedule.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @NotNull
    @NotEmpty
    @Email
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @NotNull
    @NotEmpty
    @Column(nullable = false, length = 255)
    private String password;


    @Builder
    public User(Long id, @NotNull String username, @NotNull String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
