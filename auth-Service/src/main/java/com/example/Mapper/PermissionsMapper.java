package com.example.Mapper;

import com.example.entities.PermissionsEntity;
import com.example.models.PermissionsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface PermissionsMapper {

    public PermissionsModel toModel(PermissionsEntity permissionsEntity);

    public PermissionsEntity toEntity(PermissionsModel permissionsModel);
}
