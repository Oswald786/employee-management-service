package com.example.Mapper;

import com.example.entities.UserRolesEntity;
import com.example.models.UserRolesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface UserRolesMapper {

    public UserRolesModel toModel(UserRolesEntity userRolesEntity);

    public UserRolesEntity toEntity(UserRolesModel userRolesModel);
}
