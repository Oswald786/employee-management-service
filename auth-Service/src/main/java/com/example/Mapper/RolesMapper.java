package com.example.Mapper;

import com.example.entities.RolesEntity;
import com.example.models.RolesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface RolesMapper {

    public RolesModel toModel(RolesEntity rolesEntity);

    public RolesEntity toEntity(RolesModel rolesModel);

}
