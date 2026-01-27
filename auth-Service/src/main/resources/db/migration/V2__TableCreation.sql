--Mk1 Role-Based access control model and plan

--User table
ALTER TABLE users
    ADD (
        account_status VARCHAR2(20) DEFAULT 'ACTIVE' NOT NULL,
        created_at     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
        last_login_at  TIMESTAMP
        );

ALTER TABLE users
    ADD CONSTRAINT users_username_uk UNIQUE (username);

ALTER TABLE users
    ADD CONSTRAINT users_account_status_ck
        CHECK (account_status IN ('ACTIVE', 'LOCKED', 'DISABLED'));


--Roles table
CREATE TABLE ROLES (
    role_id   NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR2(20) NOT NULL,
    description VARCHAR2(255)
);

ALTER TABLE ROLES
    ADD CONSTRAINT roles_role_name_uk UNIQUE (role_name);


--User_Roles table
CREATE TABLE USER_ROLES (
    user_id NUMBER NOT NULL,
    role_id NUMBER NOT NULL,
    CONSTRAINT user_roles_pk PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_user_roles_roles FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

--Permissions table
CREATE TABLE PERMISSIONS (
    permission_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    permission_name VARCHAR2(20) NOT NULL,
    description VARCHAR2(255)
);

ALTER TABLE PERMISSIONS
    ADD CONSTRAINT permissions_permission_name_uk UNIQUE (permission_name);

--Role_Permissions table
CREATE TABLE ROLE_PERMISSIONS (
    role_id NUMBER NOT NULL,
    permission_id NUMBER NOT NULL,
    CONSTRAINT role_permissions_pk PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role_permissions_roles FOREIGN KEY (role_id) REFERENCES roles (role_id),
    CONSTRAINT fk_role_permissions_permissions FOREIGN KEY (permission_id) REFERENCES permissions (permission_id)
);