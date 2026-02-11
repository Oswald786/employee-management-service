package com.example.controllers;

public class PermissionController {
    // =======================================================
// PermissionController
// =======================================================
// Purpose:
// Manages permission definitions only.
// Permissions represent system capabilities and are later
// assigned to roles. This controller does NOT manage roles
// or users.
//
// Design Notes:
// - permissionName is a unique, immutable, machine-readable key
// - description is human-readable and may be updated
// - permissions define WHAT actions exist, not WHO can perform them
// =======================================================


// -------------------------------------------------------
// POST /auth/permissions
// -------------------------------------------------------
// Creates a new permission.
//
// Notes:
// - permissionName acts as the stable permission key
// - permissionName must be unique and never changed
// - description provides human-readable context
// -------------------------------------------------------


// -------------------------------------------------------
// GET /auth/permissions
// -------------------------------------------------------
// Retrieves all permissions defined in the system.
//
// Notes:
// - used for administration and service visibility
// - useful for other services to reference available capabilities
// -------------------------------------------------------


// -------------------------------------------------------
// GET /auth/permissions/{permissionId}
// -------------------------------------------------------
// Retrieves a single permission by its internal identifier.
//
// Notes:
// - returns permissionId, permissionName, and description
// - used when managing or editing permissions
// -------------------------------------------------------


// -------------------------------------------------------
// PUT /auth/permissions/{permissionId}
// -------------------------------------------------------
// Updates the human-readable description of a permission.
//
// Notes:
// - permissionName (key) is immutable and must not be changed
// - only description may be updated
// - updating the description does not affect system behaviour
// -------------------------------------------------------


// -------------------------------------------------------
// Out of Scope
// -------------------------------------------------------
// This controller does NOT:
// - assign permissions to roles
// - remove permissions from roles
// - query permissions for users
// - perform access checks
//
// These concerns are handled elsewhere to maintain
// clean separation of responsibilities.
// -------------------------------------------------------
}
