package com.example.adaptors;

import com.example.Mapper.PermissionsMapper;
import com.example.entities.PermissionsEntity;
import com.example.models.PermissionCreationRequestModel;
import com.example.models.PermissionsModel;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 * ============================================================
 * Permission Adaptor – Behaviour & Rules
 * ============================================================
 *
 * A Permission consists of three fields:
 * - permissionId (generated, immutable)
 * - permissionName (immutable)
 * - description (mutable)
 *
 * Only the description field is allowed to change after creation.
 * The permissionId and permissionName must never be modified once
 * a permission has been created.
 *
 * This is to prevent accidental or silent changes to security rules.
 *
 * ------------------------------------------------------------
 * Immutability Rule
 * ------------------------------------------------------------
 *
 * If the meaning of a permission needs to change, the original
 * permission must be:
 *   1. Deleted
 *   2. Recreated as a new permission with a new ID
 *
 * Permissions are treated as effectively immutable records.
 * This ensures changes to access rules are always explicit and
 * traceable.
 *
 * ------------------------------------------------------------
 * Create Permission
 * ------------------------------------------------------------
 *
 * Creating a permission:
 * - Generates a new permissionId
 * - Assigns a permissionName
 * - Assigns a description
 *
 * This is the only way new permissions may enter the system.
 *
 * ------------------------------------------------------------
 * Retrieve Permission
 * ------------------------------------------------------------
 *
 * Supported retrieval methods:
 * - Retrieve by permissionId
 * - Retrieve by permissionName
 * - Retrieve all permissions
 * - Search for permissions matching either ID or name
 *
 * This allows both precise lookups and broader inspection
 * or filtering of permissions.
 *
 * ------------------------------------------------------------
 * Update Permission (Restricted)
 * ------------------------------------------------------------
 *
 * Only the description field may be updated.
 *
 * Updates must never allow modification of:
 * - permissionId
 * - permissionName
 *
 * Any attempt to change these values must be rejected or ignored
 * by design.
 *
 * This prevents unintended privilege changes and preserves
 * permission identity.
 *
 * ------------------------------------------------------------
 * Delete Permission
 * ------------------------------------------------------------
 *
 * Supported delete operations:
 * - Delete a specific permission (by ID or by name)
 * - Delete all permissions
 *
 * Deleting all permissions acts as a system kill-switch and may be
 * used in recovery scenarios (for example, if the system is
 * compromised or requires a full reset).
 *
 * ------------------------------------------------------------
 * Architectural Responsibility
 * ------------------------------------------------------------
 *
 * The Permission Adaptor is responsible for:
 * - Converting between Permission Models and Permission Entities
 * - Enforcing immutability rules on permissionId and permissionName
 * - Delegating persistence operations to the repository layer
 * - Returning only Models to the service layer
 *
 * No other layer is permitted to:
 * - Modify permission entities directly
 * - Access the repository without passing through the adaptor
 * - Bypass these validation and immutability rules
 *
 * ------------------------------------------------------------
 * Design Goal
 * ------------------------------------------------------------
 *
 * This design ensures:
 * - Permission meaning cannot change silently
 * - Security rules remain stable and explicit
 * - Accidental corruption of authorisation data is prevented
 * - Business logic remains isolated from persistence logic
 *
 * In an authentication system, correctness and stability take
 * priority over convenience.
 *
 * ============================================================
 */
@Singleton
public class AuthServiceDatabasePermissionsAdaptor {

    @Inject
    EntityManager entityManager;

    @Inject
    PermissionsMapper permissionsMapper;

    @Inject
    public AuthServiceDatabasePermissionsAdaptor(){}

    Logger logger = LoggerFactory.getLogger(AuthServiceDatabasePermissionsAdaptor.class);

    public void createPermission(PermissionCreationRequestModel permissionsToCreate){
        PermissionsModel permissionsModel = new PermissionsModel(null, permissionsToCreate.getPermissionName(),
                permissionsToCreate.getDescription());
        entityManager.persist(permissionsMapper.toEntity(permissionsModel));
        entityManager.flush();
        logger.info("Permission Created Successfully " + " Name: " + permissionsModel.getPermissionName());
    }

    public Optional<PermissionsModel> retrievePermissionById(Integer permissionId){
        PermissionsEntity result = entityManager.find(PermissionsEntity.class, permissionId);
        logger.info("Finding permission using Id " + " Permission Id: " + permissionId);
        PermissionsModel mappedResult = permissionsMapper.toModel(result);
        return Optional.ofNullable(mappedResult);
    }

    public Optional<PermissionsModel> retrievePermissionByName(String permissionName){
        TypedQuery<PermissionsEntity> query = entityManager.createQuery("select p from PermissionsEntity p where p.permissionName = :permissionName", PermissionsEntity.class);
        query.setParameter("permissionName", permissionName);
        logger.info("Finding permission using Name " + " Permission Name: " + permissionName);
        List<PermissionsEntity> result = query.getResultList();
        if(result.isEmpty()){
            logger.warn("Permission not found Returning empty optional");
            return Optional.empty();
        } else if (result.size() > 1) {
            logger.error("More than one permission found with same name. Returning empty optional");
            return Optional.empty();
        } else {
            logger.info("Permission found");
            return Optional.ofNullable(permissionsMapper.toModel(result.get(0)));
        }
    }

    public void updatePermissionDescription(Integer permissionId, String description){
        PermissionsEntity permissionEntity = entityManager.find(PermissionsEntity.class, permissionId);
        permissionEntity.setDescription(description);
        entityManager.merge(permissionEntity);
        entityManager.flush();
        logger.info("Permission Description Updated Successfully " + " Permission Id: " + permissionId + " Description: " + description);
    }

    public void deletePermission(Integer permissionId){
        PermissionsEntity permissionEntity = entityManager.find(PermissionsEntity.class, permissionId);
        entityManager.remove(permissionEntity);
        entityManager.flush();
        logger.info("Permission Deleted Successfully " + " Permission Id: " + permissionId);
    }

    public void deleteAllPermissions(){
        entityManager.createQuery("delete from PermissionsEntity").executeUpdate();
        logger.info("All Permissions Deleted Successfully");
    }







}
