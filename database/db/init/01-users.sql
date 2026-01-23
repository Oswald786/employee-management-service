-- ALTER SESSION SET CONTAINER = FREEPDB1;

-- =========================
-- Payroll Service
-- =========================
CREATE USER PayrollService
  IDENTIFIED BY PayrollServicePassword
  DEFAULT TABLESPACE users
  TEMPORARY TABLESPACE temp
  QUOTA UNLIMITED ON users;

GRANT CREATE SESSION TO PayrollService;
GRANT CREATE TABLE TO PayrollService;
GRANT CREATE VIEW TO PayrollService;
GRANT CREATE SEQUENCE TO PayrollService;
GRANT CREATE PROCEDURE TO PayrollService;

-- =========================
-- Performance Service
-- =========================
CREATE USER PerformanceService
  IDENTIFIED BY PerformanceServicePassword
  DEFAULT TABLESPACE users
  TEMPORARY TABLESPACE temp
  QUOTA UNLIMITED ON users;

GRANT CREATE SESSION TO PerformanceService;
GRANT CREATE TABLE TO PerformanceService;
GRANT CREATE VIEW TO PerformanceService;
GRANT CREATE SEQUENCE TO PerformanceService;
GRANT CREATE PROCEDURE TO PerformanceService;

-- =========================
-- Staff Management Service
-- =========================
CREATE USER StaffManagementService
  IDENTIFIED BY StaffManagementServicePassword
  DEFAULT TABLESPACE users
  TEMPORARY TABLESPACE temp
  QUOTA UNLIMITED ON users;

GRANT CREATE SESSION TO StaffManagementService;
GRANT CREATE TABLE TO StaffManagementService;
GRANT CREATE VIEW TO StaffManagementService;
GRANT CREATE SEQUENCE TO StaffManagementService;
GRANT CREATE PROCEDURE TO StaffManagementService;