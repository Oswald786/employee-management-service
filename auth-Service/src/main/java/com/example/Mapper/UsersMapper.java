package com.example.Mapper;

import com.example.entities.UsersEntity;
import com.example.models.UsersModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jsr330")
public interface UsersMapper {

    public UsersEntity toEntity(UsersModel usersModel);

    public UsersModel toModel(UsersEntity usersEntity);
}
