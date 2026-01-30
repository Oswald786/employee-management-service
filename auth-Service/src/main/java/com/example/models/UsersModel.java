package com.example.models;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Serdeable
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersModel {

    private String userId;

    private String username;

    private String password;

    private String accountStatus;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;
}
