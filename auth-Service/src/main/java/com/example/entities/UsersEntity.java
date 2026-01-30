package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Serdeable
@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UsersEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD_HASH")
    private String password;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "LAST_LOGIN_AT")
    private LocalDateTime lastLoginAt;
}
