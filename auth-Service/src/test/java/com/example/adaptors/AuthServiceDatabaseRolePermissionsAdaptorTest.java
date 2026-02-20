package com.example.adaptors;

import com.example.Mapper.RolePermissionsMapper;
import com.example.entities.RolePermissionsEntity;
import com.example.models.RolePermissionsModel;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.relation.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("AuthServiceDatabaseRolePermissionsAdaptorTest")
@MicronautTest
class AuthServiceDatabaseRolePermissionsAdaptorTest {

    @Mock
    EntityManager entityManager;

    @Mock
    RolePermissionsMapper rolePermissionsMapper;

    @InjectMocks
    AuthServiceDatabaseRolePermissionsAdaptor authServiceDatabaseRolePermissionsAdaptor;

    @Test
    @DisplayName("Should persist role-permission link when valid model is supplied")
    void createRolePermissionLink_ShouldPersistEntity_WhenValidModelSupplied() {

        //Arrange
        RolePermissionsModel rolePermissionsModel = new RolePermissionsModel("5","9");
        RolePermissionsEntity rolePermissionsEntity = new RolePermissionsEntity();
        rolePermissionsEntity.setRoleId("5");
        rolePermissionsEntity.setPermissionId("9");
        when(rolePermissionsMapper.toEntity(rolePermissionsModel)).thenReturn(rolePermissionsEntity);

        //Act
        authServiceDatabaseRolePermissionsAdaptor.createRolePermissionLink(rolePermissionsModel);

        //Assert
        verify(entityManager).persist(rolePermissionsEntity);
        verify(entityManager).flush();
        verify(rolePermissionsMapper).toEntity(rolePermissionsModel);
    }

    @Test
    @DisplayName("Should return mapped role-permission links when role ID exists")
    void retrieveRolePermissionLinksUsingRoleId_ShouldReturnMappedModels_WhenLinksExist() {
        //Arrange
        Integer roleId = 6;
        RolePermissionsEntity rolePermissionsEntity1 = new RolePermissionsEntity();
        rolePermissionsEntity1.setRoleId("6");
        rolePermissionsEntity1.setPermissionId("9");

        RolePermissionsEntity rolePermissionsEntity2 = new RolePermissionsEntity();
        rolePermissionsEntity2.setRoleId("6");
        rolePermissionsEntity2.setPermissionId("10");

        List<RolePermissionsEntity> rolePermissionsEntities = List.of(rolePermissionsEntity1, rolePermissionsEntity2);

        TypedQuery<RolePermissionsEntity> query = mock(TypedQuery.class);

        when(entityManager.createQuery("select rp from RolePermissionsEntity rp where rp.roleId = :roleId", RolePermissionsEntity.class)).thenReturn(query);
        when(query.setParameter("roleId", roleId)).thenReturn(query);
        when(query.getResultList()).thenReturn(rolePermissionsEntities);
        when(rolePermissionsMapper.toModel(rolePermissionsEntity1)).thenReturn(new RolePermissionsModel("6","9"));
        when(rolePermissionsMapper.toModel(rolePermissionsEntity2)).thenReturn(new RolePermissionsModel("6","10"));

        //Act
        List<RolePermissionsModel> result = authServiceDatabaseRolePermissionsAdaptor.retrieveRolePermissionLinksUsingRoleId(roleId);

        //Assert
        assertEquals(2, result.size());
        verify(entityManager).createQuery("select rp from RolePermissionsEntity rp where rp.roleId = :roleId", RolePermissionsEntity.class);
        verify(query).setParameter("roleId", roleId);
        verify(query).getResultList();
        verify(rolePermissionsMapper,times(2)).toModel(any(RolePermissionsEntity.class));
    }

    @Test
    void retrieveRolePermissionLinksUsingPermissionId() {
    }

    @Test
    void deleteRolePermissionLink() {
    }

    @Test
    void deleteAllRolePermissionsForRole() {
    }
}