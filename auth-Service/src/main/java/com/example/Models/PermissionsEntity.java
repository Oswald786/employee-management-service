package com.example.Models;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import lombok.Getter;
import lombok.Setter;

@Serdeable
@Entity
@Getter
@Setter
@Table(name = "PERMISSIONS")
public class PermissionsEntity {

    @Id
    @Column(name = "PERMISSION_ID")
    private String PermissionId;

    @NotNull
    @Column(name = "PERMISSION_NAME")
    private String PermissionName;

    @Nullable
    @Column(name = "DESCRIPTION")
    private String Description;
}
