package com.example.adaptors;

/*
 * ============================================================
 * Role-Permission Adaptor – Behaviour & Rules
 * ============================================================
 *
 * This adaptor manages the relationship between Roles and Permissions.
 *
 * Each record represents a link between:
 * - roleId
 * - permissionId
 *
 * A role may be linked to many permissions.
 * A permission may be linked to many roles.
 *
 * The combination of (roleId + permissionId) must be unique.
 * This prevents the same permission being assigned to the same
 * role more than once.
 *
 * ------------------------------------------------------------
 * Create Role-Permission Link
 * ------------------------------------------------------------
 *
 * Creating a role-permission link:
 * - Requires an existing roleId
 * - Requires an existing permissionId
 *
 * This operation assigns a permission to a role.
 *
 * A role may have multiple permissions, therefore the same
 * roleId may appear multiple times with different permissionIds.
 *
 * A permission may be linked to multiple roles, therefore the
 * same permissionId may appear multiple times with different roleIds.
 *
 * ------------------------------------------------------------
 * Remove Role-Permission Link
 * ------------------------------------------------------------
 *
 * Removing a role-permission link:
 * - Removes a specific permission from a specific role
 * - Uses both roleId and permissionId to identify the link
 *
 * This revokes that permission from the role without deleting
 * the role or the permission themselves.
 *
 * This operation is critical for access control management.
 *
 * ------------------------------------------------------------
 * Update Role-Permission Link (Not Supported)
 * ------------------------------------------------------------
 *
 * Updating a role-permission link is intentionally not supported.
 *
 * This prevents the risk of accidentally changing which permission
 * a role is linked to, which could introduce security issues.
 *
 * To change a role's permissions, the system must:
 * - Create a new role-permission link, or
 * - Remove an existing role-permission link
 *
 * This design enforces explicit and safe permission changes.
 *
 * ------------------------------------------------------------
 * Delete Role-Permission Links
 * ------------------------------------------------------------
 *
 * Supported delete operations:
 * - Delete all role-permission links for a given roleId
 * - Delete all role-permission links (system reset / recovery)
 *
 * Deleting all links for a specific roleId removes all permissions
 * assigned to that role.
 *
 * Deleting by permissionId is intentionally not supported, as
 * removing all links for a given permission could lead to
 * unintended access control errors across multiple roles.
 *
 * ------------------------------------------------------------
 * Architectural Responsibility
 * ------------------------------------------------------------
 *
 * The Role-Permission Adaptor is responsible for:
 * - Managing associations between roleIds and permissionIds
 * - Enforcing uniqueness of the (roleId, permissionId) pair
 * - Delegating persistence operations to the repository layer
 * - Returning only Models to the service layer
 *
 * This adaptor must not:
 * - Create or modify Role records
 * - Create or modify Permission records
 *
 * It only manages the relationship between them.
 *
 * ------------------------------------------------------------
 * Design Goal
 * ------------------------------------------------------------
 *
 * This design ensures:
 * - Roles can be assigned multiple permissions safely
 * - Permissions can be reused across multiple roles
 * - Duplicate assignments are prevented
 * - Permission changes are explicit (create/remove only)
 * - Accidental security misconfiguration is avoided
 *
 * This mapping layer defines what actions each role is allowed
 * to perform and is central to the authorisation system.
 *
 * ============================================================
 */


import com.example.Mapper.RolePermissionsMapper;
import com.example.entities.RolePermissionsEntity;
import com.example.models.RolePermissionsModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@NoArgsConstructor
public class AuthServiceDatabaseRolePermissionsAdaptor {

    Logger logger = LoggerFactory.getLogger(AuthServiceDatabaseRolePermissionsAdaptor.class);

    @Inject
   EntityManager entityManager;

    @Inject
    RolePermissionsMapper rolePermissionsMapper;

    public void createRolePermissionLink (RolePermissionsModel rolePermissionsModel){
        //Set up the entity and persist it
        entityManager.persist(rolePermissionsMapper.toEntity(rolePermissionsModel));
        entityManager.flush();
        logger.info("Role-Permission Link Created Successfully");
    }

    public List<RolePermissionsModel> retrieveRolePermissionLinksUsingRoleId(Integer roleId){
        TypedQuery<RolePermissionsEntity> query = entityManager.createQuery("select rp from RolePermissionsEntity rp where rp.roleId = :roleId", RolePermissionsEntity.class);
        logger.info("Retrieving Role-Permission Links for Role Id: {}", roleId);
        query.setParameter("roleId", roleId);
        List<RolePermissionsEntity> result = query.getResultList();
        result.forEach(RolePermissionsEntity -> logger.info("Role-Permission Link Found: {}",RolePermissionsEntity.toString()));
        return result.stream().map(rolePermissionsMapper::toModel).toList();
    }

    public List<RolePermissionsModel> retrieveRolePermissionLinksUsingPermissionId(Integer permissionId){
        TypedQuery<RolePermissionsEntity> query = entityManager.createQuery("select rp from RolePermissionsEntity rp where rp.permissionId = :permissionId", RolePermissionsEntity.class);
        logger.info("Retrieving Role-Permission Links for Permission Id:{} ", permissionId);
        query.setParameter("permissionId", permissionId);
        List<RolePermissionsEntity> result = query.getResultList();
        result.forEach(RolePermissionsEntity -> logger.info("Role-Permission Link Found: {} ", RolePermissionsEntity.toString()));
        return result.stream().map(rolePermissionsMapper::toModel).toList();
    }

    public void deleteRolePermissionLink(Integer roleId, Integer permissionId){
        entityManager.createQuery("delete from RolePermissionsEntity where roleId = :roleId and permissionId = :permissionId")
                .setParameter("roleId", roleId).setParameter("permissionId", permissionId).executeUpdate();
        logger.info("Role-Permission Link Deleted Successfully");
    }

    public void deleteAllRolePermissionsForRole(Integer roleId){
        entityManager.createQuery("delete from RolePermissionsEntity where roleId = :roleId")
                .setParameter("roleId", roleId).executeUpdate();
        logger.info("All Role-Permission Links Deleted Successfully for Role Id: {}", roleId);
    }

}
