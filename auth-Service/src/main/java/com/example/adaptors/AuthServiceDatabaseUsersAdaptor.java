package com.example.adaptors;

public class AuthServiceDatabaseUsersAdaptor {

    /*
     * ============================================================
     * User Adaptor – Behaviour & Rules
     * ============================================================
     *
     * A User consists of the following fields:
     * - userId (generated, unique)
     * - username (unique)
     * - passwordHash
     * - accountStatus (e.g. ACTIVE / INACTIVE, default = ACTIVE)
     * - createdAt (set on creation, immutable)
     * - lastLoginAt (updated on login)
     *
     * Both userId and username must be unique.
     *
     * ------------------------------------------------------------
     * Create User
     * ------------------------------------------------------------
     *
     * Creating a user requires:
     * - A unique username
     * - A passwordHash (already hashed before persistence)
     * - An accountStatus (defaults to ACTIVE if not provided)
     *
     * On creation:
     * - createdAt is set to the current timestamp
     * - lastLoginAt is also set to the current timestamp
     *
     * The adaptor must ensure:
     * - No duplicate usernames are allowed
     * - No plain-text passwords are persisted
     *
     * ------------------------------------------------------------
     * Retrieve User
     * ------------------------------------------------------------
     *
     * Users must be retrievable by:
     * - userId
     * - username
     *
     * It must also be possible to:
     * - Retrieve all users (admin use case)
     *
     * Retrieval is separated by responsibility:
     * - The User-Role adaptor is used to determine which userIds
     *   are related to a given role.
     * - The User adaptor is then used to retrieve full user
     *   information using those userIds.
     *
     * This keeps role-assignment logic separate from user data logic.
     *
     * Supported retrieval views include:
     *
     * 1) Account information:
     *    - accountStatus
     *    - createdAt
     *    - lastLoginAt
     *
     * 2) Security information:
     *    - username
     *    - passwordHash
     *
     * These views are conceptually separated to avoid exposing
     * unnecessary sensitive data when not required.
     *
     * ------------------------------------------------------------
     * Update User
     * ------------------------------------------------------------
     *
     * The following fields may be updated:
     * - username (must remain unique)
     * - passwordHash
     * - accountStatus
     * - lastLoginAt
     *
     * The following fields must never be updated:
     * - userId
     * - createdAt
     *
     * Updating lastLoginAt is performed when a user successfully
     * authenticates.
     *
     * ------------------------------------------------------------
     * Delete User
     * ------------------------------------------------------------
     *
     * Deleting a user:
     * - Removes the entire user record
     * - Must be performed by searching via:
     *   - userId, or
     *   - username
     *
     * Partial deletion of user data is not supported.
     *
     * Any associated user-role mappings must be handled separately
     * by the User-Role adaptor.
     *
     * ------------------------------------------------------------
     * Architectural Responsibility
     * ------------------------------------------------------------
     *
     * The User Adaptor is responsible for:
     * - Managing creation, retrieval, update, and deletion of users
     * - Enforcing uniqueness of username
     * - Delegating persistence operations to the repository layer
     * - Converting between User Models and User Entities
     * - Returning only Models to the service layer
     *
     * This adaptor must not:
     * - Determine which users belong to which roles
     * - Manage role assignments
     * - Manage permission assignments
     *
     * Those concerns belong to the User-Role and Role-Permission
     * adaptors respectively.
     *
     * ------------------------------------------------------------
     * Design Goal
     * ------------------------------------------------------------
     *
     * This design ensures:
     * - User identity remains stable (via userId)
     * - Credentials are handled securely
     * - Account state is clearly defined and manageable
     * - Login activity can be tracked safely
     * - Role relationships are resolved externally
     * - Business logic is isolated from persistence logic
     *
     * The User Adaptor acts as the controlled gateway for all
     * user-related data entering and leaving the database.
     *
     * ============================================================
     */

}
