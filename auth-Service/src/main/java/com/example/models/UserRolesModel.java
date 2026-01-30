package com.example.models;


import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Serdeable
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRolesModel {

    private String userId;

    private String roleId;
}
