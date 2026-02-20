package com.example.adaptors;

import com.example.Mapper.PermissionsMapper;
import com.example.entities.PermissionsEntity;
import com.example.models.PermissionCreationRequestModel;
import com.example.models.PermissionsModel;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
    @DisplayName("shouldReturnPermission_whenValidIdSupplied")
    void shouldReturnPermission_whenValidIdSupplied() {
        //Arrange
        Integer permissionId = 1;
        PermissionsEntity permissionsEntity = new PermissionsEntity();
        permissionsEntity.setPermissionName("testPermission");
        permissionsEntity.setDescription("testDescription");

        PermissionsModel permissionsModel = new PermissionsModel();
        permissionsModel.setPermissionName("testPermission");
        permissionsModel.setDescription("testDescription");

        when(entityManager.find(PermissionsEntity.class, permissionId)).thenReturn(permissionsEntity);
        when(permissionsMapper.toModel(permissionsEntity)).thenReturn(permissionsModel);

        InOrder inOrder = inOrder(entityManager,permissionsMapper);

        //Act
        Optional<PermissionsModel> result = authServiceDatabasePermissionsAdaptor.retrievePermissionById(permissionId);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(permissionsModel, result.get());
        inOrder.verify(entityManager).find(PermissionsEntity.class, permissionId);
        inOrder.verify(permissionsMapper).toModel(permissionsEntity);
    }

    @Test
    @DisplayName("retrievePermissionByName returns empty Optional when no permission exists with given name")
    void retrievePermissionByNameReturnsEmptyOptionalWhenNoPermissionExistsWithName() {

        //Arrange
        String permissionName = "testPermission";
        TypedQuery<PermissionsEntity> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PermissionsEntity.class))).thenReturn(query);
        when(query.setParameter("permissionName", permissionName)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        //Act
        Optional<PermissionsModel> result = authServiceDatabasePermissionsAdaptor.retrievePermissionByName(permissionName);

        //Assert
        assertTrue(result.isEmpty());
        verify(entityManager).createQuery("select p from PermissionsEntity p where p.permissionName = :permissionName", PermissionsEntity.class);
        verify(query).setParameter("permissionName", permissionName);
        verify(query).getResultList();
        verifyNoMoreInteractions(entityManager, query);

    }

    @Test
    @DisplayName("retrievePermissionByName returns empty Optional when duplicate permissions are found")
    void retrievePermissionByNameReturnsEmptyOptionalWhenDuplicatePermissionsAreFound() {

        //Arrange
        String permissionName = "testPermission";
        TypedQuery<PermissionsEntity> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PermissionsEntity.class))).thenReturn(query);
        when(query.setParameter("permissionName", permissionName)).thenReturn(query);
        when(query.getResultList()).thenReturn(List.of(new PermissionsEntity(), new PermissionsEntity()));

        //Act
        Optional<PermissionsModel> result = authServiceDatabasePermissionsAdaptor.retrievePermissionByName(permissionName);

        //Assert
        assertTrue(result.isEmpty());
        verify(entityManager).createQuery("select p from PermissionsEntity p where p.permissionName = :permissionName", PermissionsEntity.class);
        verify(query).setParameter("permissionName", permissionName);
        verify(query).getResultList();
        verifyNoMoreInteractions(entityManager, query);
    }

    @Test
    @DisplayName("retrievePermissionByName returns mapped permission when exactly one result is found")
    void retrievePermissionByNameReturnsMappedPermissionWhenExactlyOneResultIsFound() {
        //Arrange
        String permissionName = "testPermission";
        TypedQuery<PermissionsEntity> query = mock(TypedQuery.class);
        when(entityManager.createQuery(anyString(), eq(PermissionsEntity.class))).thenReturn(query);
        when(query.setParameter("permissionName", permissionName)).thenReturn(query);
        PermissionsEntity permissionsEntity = new PermissionsEntity();
        permissionsEntity.setPermissionName(permissionName);
        when(query.getResultList()).thenReturn(List.of(permissionsEntity));
        PermissionsModel permissionsModel = new PermissionsModel();
        permissionsModel.setPermissionName(permissionName);
        when(permissionsMapper.toModel(permissionsEntity)).thenReturn(permissionsModel);

        //Act
        Optional<PermissionsModel> result = authServiceDatabasePermissionsAdaptor.retrievePermissionByName(permissionName);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(permissionsModel, result.get());
        verify(entityManager).createQuery("select p from PermissionsEntity p where p.permissionName = :permissionName", PermissionsEntity.class);
        verify(query).setParameter("permissionName", permissionName);
        verify(query).getResultList();
        verify(permissionsMapper).toModel(permissionsEntity);
    }


    @Test
    @DisplayName("updatePermissionDescription should update and return permission when permission exists")
    void updatePermissionDescription_whenPermissionExists_updatesAndReturnsPermission() {
        //Arrange
        Integer permissionId = 1;
        String description = "testUpdatedDescription";
        PermissionsEntity permissionsEntity = new PermissionsEntity();
        when(entityManager.find(PermissionsEntity.class, permissionId)).thenReturn(permissionsEntity);


        //Act
        authServiceDatabasePermissionsAdaptor.updatePermissionDescription(permissionId, description);


        //Assert
        verify(entityManager).find(PermissionsEntity.class, permissionId);

        ArgumentCaptor<PermissionsEntity> captor =
                ArgumentCaptor.forClass(PermissionsEntity.class);

        verify(entityManager).merge(captor.capture());

        verify(entityManager).flush();

        PermissionsEntity captured = captor.getValue();

        assertEquals(description, captured.getDescription());

        verifyNoMoreInteractions(entityManager);
    }

    @Test
    @DisplayName("Should delete permission entity successfully when valid permission ID is provided")
    void deletePermission_ShouldDeleteEntity_WhenPermissionExists() {
        //Arrange
        Integer permissionId = 1;
        PermissionsEntity permissionEntity = new PermissionsEntity();
        permissionEntity.setPermissionId("1");
        permissionEntity.setPermissionName("testPermission");
        permissionEntity.setDescription("testDescription");
        when(entityManager.find(PermissionsEntity.class, permissionId)).thenReturn(permissionEntity);

        //Act
        authServiceDatabasePermissionsAdaptor.deletePermission(permissionId);

        //Assert
        verify(entityManager).find(PermissionsEntity.class, permissionId);
        verify(entityManager).remove(permissionEntity);
        verify(entityManager).flush();
    }

    @Test
    @DisplayName("Kill switch to delete all permissions on database works")
    void deleteAllPermissions_shouldDeleteAllPermissions_whenCalled() {
        // Arrange
        Query query = mock(Query.class);

        when(entityManager.createQuery(anyString()))
                .thenReturn(query);

        when(query.executeUpdate())
                .thenReturn(5);

        // Act
        authServiceDatabasePermissionsAdaptor.deleteAllPermissions();

        // Assert
        verify(entityManager).createQuery("DELETE FROM PermissionsEntity p");
        verify(query).executeUpdate();

        }

}