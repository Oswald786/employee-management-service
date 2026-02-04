package com.example.adaptors;

public class AuthServiceDatabasePermissionsAdaptor {
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

}
