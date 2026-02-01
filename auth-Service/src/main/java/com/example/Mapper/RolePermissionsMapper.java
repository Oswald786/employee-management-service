package com.example.Mapper;


import com.example.entities.RolePermissionsEntity;
import com.example.models.RolePermissionsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface RolePermissionsMapper {

    public RolePermissionsModel toModel(RolePermissionsEntity rolePermissionsEntity);

    public RolePermissionsEntity toEntity(RolePermissionsModel rolePermissionsModel);
}
