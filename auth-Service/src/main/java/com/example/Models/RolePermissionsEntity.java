package com.example.Models;

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
@Getter
@Setter
@Table(name = "ROLE_PERMISSIONS")
public class RolePermissionsEntity {

    @Id
    @Column(name = "ROLE_ID")
    @NotNull
    private String roleId;

    @Column(name = "PERMISSION_ID")
    @NotNull
    private String permissionId;
}
