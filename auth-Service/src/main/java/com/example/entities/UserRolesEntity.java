package com.example.entities;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Serdeable
@Entity
@Table(name = "USER_ROLES")
@Getter
@Setter
public class UserRolesEntity {

    @Id
    @Column(name = "USER_ID")
    @NotNull
    private String userId;

    @Column(name = "ROLE_ID")
    @NotNull
    private String roleId;

}
