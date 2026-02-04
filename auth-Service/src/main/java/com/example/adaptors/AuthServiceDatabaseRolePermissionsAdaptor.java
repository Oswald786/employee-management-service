package com.example.adaptors;

public class AuthServiceDatabaseRolePermissionsAdaptor {
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

}
