package com.example.controllers;

import io.micronaut.http.annotation.Controller;

@Controller("/auth")
public class AuthController {

// =======================================================
// AuthController
// =======================================================
// Purpose:
// Handles authentication-related operations.
// Provides endpoints for user login and registration.
//
// Design Notes:
// - authentication is delegated to Micronaut via AuthenticationProvider
// - AuthService handles domain-specific login and registration logic
// - JWT tokens are issued only after successful authentication
// =======================================================


// -------------------------------------------------------
// POST /auth/login
// -------------------------------------------------------
// Login endpoint used for authentication.
//
// Notes:
// - request data (username and password) is verified by the AuthenticationProvider
// - AuthService performs additional business logic such as:
//   - updating audit fields (e.g. lastLoginAt)
//   - applying domain-specific rules
// - on successful authentication, a JWT token is issued
// -------------------------------------------------------


// -------------------------------------------------------
// POST /auth/register
// -------------------------------------------------------
// Registers a new user in the system.
//
// Notes:
// - validates input (not null, not empty)
// - checks that the username is not already taken
// - hashes the provided password before storage
// - maps request data to UserEntity
// - persists the user to the database
// - maps the saved entity back to a UserModel and returns it
// -------------------------------------------------------


// -------------------------------------------------------
// Out of Scope
// -------------------------------------------------------
// This controller does NOT:
// - manage roles or permissions
// - perform access checks
// - issue tokens directly
//
// Role management is handled by RoleController.
// Permission management is handled by PermissionController.
// -------------------------------------------------------
}
