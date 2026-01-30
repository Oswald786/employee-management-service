package com.example.models;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Introspected
@Serdeable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PermissionsModel {
    private String permissionId;

    private String permissionName;

    private String description;
}
