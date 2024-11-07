package com.example.schedule.domain.entity;

import com.example.schedule.domain.base.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
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


    public User(Long id, String username, String email, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;

    }
}
