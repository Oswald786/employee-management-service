package com.example.adaptors;

import com.example.Mapper.PermissionsMapper;
import com.example.entities.PermissionsEntity;
import com.example.models.PermissionCreationRequestModel;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MicronautTest
@DisplayName("AuthServiceDatabasePermissionsAdaptorTest")
@ExtendWith(MockitoExtension.class)
class AuthServiceDatabasePermissionsAdaptorTest {

    @Mock
    EntityManager entityManager;
    @Mock
    PermissionsMapper permissionsMapper;

    @InjectMocks
    AuthServiceDatabasePermissionsAdaptor authServiceDatabasePermissionsAdaptor;

    @Test
    @DisplayName("shouldPersistPermission_whenValidRequestSupplied")
    void shouldPersistPermission_whenValidRequestSupplied() {
        //Arrange
        PermissionCreationRequestModel permissionCreationRequestModel = new PermissionCreationRequestModel("testPermission", "testDescription");
        PermissionsEntity mappedEntity = new PermissionsEntity();
        mappedEntity.setPermissionName("testPermission");
        mappedEntity.setDescription("testDescription");

        when(permissionsMapper.toEntity(any())).thenReturn(mappedEntity);

        //Act
        authServiceDatabasePermissionsAdaptor.createPermission(permissionCreationRequestModel);

        ArgumentCaptor<PermissionsEntity> argumentCaptor = ArgumentCaptor.forClass(PermissionsEntity.class);

        //Assert
        verify(entityManager).persist(argumentCaptor.capture());
        assertEquals(permissionCreationRequestModel.getPermissionName(), argumentCaptor.getValue().getPermissionName());
        assertEquals(permissionCreationRequestModel.getDescription(), argumentCaptor.getValue().getDescription());
        verify(entityManager).flush();
    }

    @Test
    void retrievePermissionById() {
    }

    @Test
    void retrievePermissionByName() {
    }

    @Test
    void updatePermissionDescription() {
    }

    @Test
    void deletePermission() {
    }

    @Test
    void deleteAllPermissions() {
    }
}