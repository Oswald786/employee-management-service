package com.example.Models;

import io.micronaut.core.annotation.Nullable;
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
@Table(name = "ROLES")
@Getter
@Setter
public class RolesEntity {

    @Id
    @Column(name = "ROLE_ID")
    @NotNull
    private String roleId;

    @Column(name = "ROLE_NAME")
    @NotNull
    private String roleName;

    @Column(name = "DESCRIPTION")
    @Nullable
    private String description;
}
