package com.example.controllers;

public class UserController {

    // =======================================================
// UserController
// =======================================================
// Purpose:
// Manages user account information and account state.
// This controller exposes non-sensitive user data only
// and does NOT handle authentication credentials.
//
// Design Notes:
// - usernames and passwords are handled by AuthController only
// - UserController focuses on identity, status, and audit data
// - sensitive authentication data is never exposed here
// =======================================================


// -------------------------------------------------------
// GET /auth/users
// -------------------------------------------------------
// Retrieves all users in the system.
//
// Notes:
// - returns non-sensitive user information only
// - does NOT return passwords or authentication secrets
// - used for administration and auditing
// -------------------------------------------------------


// -------------------------------------------------------
// GET /auth/users/{userId}
// -------------------------------------------------------
// Retrieves a specific user by internal identifier.
//
// Notes:
// - returns userId, account status, and audit fields
// - does NOT expose username or password
// -------------------------------------------------------


// -------------------------------------------------------
// GET /auth/users/{userId}/status
// -------------------------------------------------------
// Retrieves the current account status of a user.
//
// Notes:
// - used to check whether an account is active, disabled, or locked
// - does not expose authentication credentials
// -------------------------------------------------------


// -------------------------------------------------------
// PUT /auth/users/{userId}/status
// -------------------------------------------------------
// Updates the account status of a user.
//
// Notes:
// - used to enable, disable, or lock a user account
// - does not modify authentication credentials
// -------------------------------------------------------


// -------------------------------------------------------
// GET /auth/users/{userId}/audit
// -------------------------------------------------------
// Retrieves audit information for a user.
//
// Notes:
// - includes fields such as createdAt and lastLoginAt
// - used for monitoring and security auditing
// -------------------------------------------------------


// -------------------------------------------------------
// Out of Scope
// -------------------------------------------------------
// This controller does NOT:
// - authenticate users
// - expose usernames or passwords
// - manage roles or permissions
//
// Authentication is handled by AuthController.
// Role and permission assignment is handled by RoleController.
// -------------------------------------------------------

}
