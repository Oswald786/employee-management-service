package com.example.adaptors;

public class AuthServiceDatabaseUserRolesAdaptor {

    /*
     * ============================================================
     * User-Role Adaptor – Behaviour & Rules
     * ============================================================
     *
     * This adaptor manages the relationship between Users and Roles.
     *
     * Each record represents a link between:
     * - userId
     * - roleId
     *
     * A user may be linked to multiple roles.
     * A role may be linked to multiple users.
     *
     * The combination of (userId + roleId) must be unique.
     * This prevents the same role being assigned to the same user
     * more than once.
     *
     * ------------------------------------------------------------
     * Create User-Role Link
     * ------------------------------------------------------------
     *
     * Creating a user-role link:
     * - Requires an existing userId
     * - Requires an existing roleId
     *
     * This operation assigns a role to a user.
     *
     * A user may have multiple roles, therefore the same userId
     * may appear multiple times with different roleIds.
     *
     * A role may be linked to multiple users, therefore the same
     * roleId may appear multiple times with different userIds.
     *
     * ------------------------------------------------------------
     * Retrieve User-Role Links
     * ------------------------------------------------------------
     *
     * Supported retrieval methods include:
     * - Retrieve all roles for a given userId
     * - Retrieve all users for a given roleId
     * - Retrieve a specific user-role link
     * - Retrieve all user-role links
     *
     * These methods allow the system to determine:
     * - Which roles are assigned to a user
     * - Which users belong to a given role
     * - The full set of user-role relationships
     *
     * ------------------------------------------------------------
     * Update User-Role Link (Not Supported)
     * ------------------------------------------------------------
     *
     * Updating a user-role link is intentionally not supported.
     *
     * Modifying an existing link could result in accidental or
     * unclear security changes.
     *
     * To change a user’s roles, the system must:
     * - Create a new user-role link, and/or
     * - Remove an existing user-role link
     *
     * This enforces explicit and auditable role assignment changes.
     *
     * ------------------------------------------------------------
     * Delete User-Role Links
     * ------------------------------------------------------------
     *
     * Supported delete operations:
     * - Remove a specific role from a specific user
     * - Remove all roles from a specific user
     * - Remove all user-role links (system reset / recovery)
     *
     * Removing a link revokes that role from the user without
     * deleting the user or the role themselves.
     *
     * ------------------------------------------------------------
     * Architectural Responsibility
     * ------------------------------------------------------------
     *
     * The User-Role Adaptor is responsible for:
     * - Managing associations between userIds and roleIds
     * - Enforcing uniqueness of the (userId, roleId) pair
     * - Delegating persistence operations to the repository layer
     * - Returning only Models to the service layer
     *
     * This adaptor must not:
     * - Create or modify User records
     * - Create or modify Role records
     *
     * It only manages the relationship between them.
     *
     * ------------------------------------------------------------
     * Design Goal
     * ------------------------------------------------------------
     *
     * This design ensures:
     * - Users can be assigned multiple roles
     * - Roles can be shared across many users
     * - Duplicate assignments are prevented
     * - Role changes are explicit (create/remove only)
     * - Accidental security misconfiguration is avoided
     *
     * This mapping layer defines which roles are assigned to each
     * user and is central to the authorisation system.
     *
     * ============================================================
     */

}
