
CREATE DATABASE tracker
ON 
(NAME = tracker,
FILENAME = 'C:\Project\tracker.mdf',
    SIZE = 10,
    MAXSIZE = 50,
    FILEGROWTH = 5 )
LOG ON
(NAME = tacker_log,
FILENAME = 'C:\Project\tracker.ldf',
    SIZE = 5MB,
    MAXSIZE = 25MB,
    FILEGROWTH = 5MB ) ;

CREATE TABLE tracker.dbo.role(
	roleID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	roleName varchar(20) NOT NULL );

CREATE TABLE tracker.dbo.permission_group(
	groupID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	groupName varchar(30) NOT NULL );

CREATE TABLE tracker.dbo.permission(
	permID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	readPer int NOT NULL CONSTRAINT FK_perm_permGroup_id REFERENCES tracker.dbo.permission_group(groupID),
	writePer int NOT NULL CONSTRAINT FK2_perm_permGroup_id REFERENCES tracker.dbo.permission_group(groupID));

CREATE TABLE tracker.dbo.department(
	deptID int IDENTITY(10,10) NOT NULL PRIMARY KEY,
	deptName varchar(30) NOT NULL,
	permID int NOT NULL	CONSTRAINT FK_dept_perm_id REFERENCES  tracker.dbo.permission(permID));

CREATE TABLE tracker.dbo.employee(
	empID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	lastName varchar(25) NOT NULL,
	firstName varchar(15) NOT NULL,
	surName varchar(20) NULL,
	jobTitle varchar(50) NOT NULL,
	login varchar(10) NOT NULL UNIQUE, 
	pass varchar(100) NOT NULL,
	deptID int NOT NULL CONSTRAINT FK_emp_dept_id REFERENCES  tracker.dbo.department(deptID),
	roleID int NOT NULL CONSTRAINT FK_emp_role_id REFERENCES  tracker.dbo.role(roleID),
	permID int NOT NULL	CONSTRAINT FK_emp_perm_id REFERENCES  tracker.dbo.permission(permID));

CREATE TABLE tracker.dbo.production_unit(
	unitID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	unitTitle varchar(150) NOT NULL UNIQUE,
	unitDesc varchar(500) NULL,
	conclusionDate date NOT NULL,
	expireDate date NOT NULL,
	ownerID int NOT NULL CONSTRAINT FK_unit_emp_id REFERENCES  tracker.dbo.employee(empID),
	permID int NOT NULL	CONSTRAINT FK_unit_perm_id REFERENCES  tracker.dbo.permission(permID));

CREATE TABLE tracker.dbo.activity_type(
	typeID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	typeTitle varchar(100) NOT NULL,
	deptID int NOT NULL CONSTRAINT FK_atype_dept_id REFERENCES  tracker.dbo.department(deptID),
	permID int NOT NULL	CONSTRAINT FK_atype_perm_id REFERENCES  tracker.dbo.permission(permID));

CREATE TABLE tracker.dbo.activity(
	activityID int IDENTITY(1,1) PRIMARY KEY NOT NULL,
	typeID int NOT NULL CONSTRAINT FK_active_atype_id REFERENCES tracker.dbo.activity_type(typeID),
	unitID int NOT NULL CONSTRAINT FK_active_unit_id REFERENCES  tracker.dbo.production_unit(unitID),
	ownerID int NOT NULL CONSTRAINT FK_active_emp_id REFERENCES  tracker.dbo.employee(empID),
	dateWorks date NOT NULL,
	timeWorked decimal(2, 2) NOT NULL,
	activityDesc varchar(500) NULL,
	permID int NOT NULL	CONSTRAINT FK_active_perm_id REFERENCES  tracker.dbo.permission(permID));

CREATE TABLE tracker.dbo.group_member(
	memberID int IDENTITY(1,1) NOT NULL PRIMARY KEY,
	groupID int NOT NULL CONSTRAINT FK_memb_permGroup_id REFERENCES tracker.dbo.permission_group(groupID),
	roleID int NOT NULL CONSTRAINT FK_memb_role_id REFERENCES  tracker.dbo.role(roleID));

INSERT INTO tracker.dbo.role (roleName) VALUES ('manager01');
INSERT INTO tracker.dbo.role (roleName) VALUES ('manager02');
INSERT INTO tracker.dbo.role (roleName) VALUES ('manager03');
INSERT INTO tracker.dbo.role (roleName) VALUES ('employee01');

INSERT INTO tracker.dbo.permission_group (groupName) VALUES ('domain administrators');
INSERT INTO tracker.dbo.permission_group (groupName) VALUES ('domain users');
INSERT INTO tracker.dbo.permission_group (groupName) VALUES ('unit writers');
INSERT INTO tracker.dbo.permission_group (groupName) VALUES ('employee writers');
INSERT INTO tracker.dbo.permission_group (groupName) VALUES ('employee readers');

INSERT INTO tracker.dbo.permission (readPer, writePer) VALUES (1, 1);
INSERT INTO tracker.dbo.permission (readPer, writePer) VALUES (2, 1);
INSERT INTO tracker.dbo.permission (readPer, writePer) VALUES (2, 2);
INSERT INTO tracker.dbo.permission (readPer, writePer) VALUES (2, 3);
INSERT INTO tracker.dbo.permission (readPer, writePer) VALUES (5, 4);

INSERT INTO tracker.dbo.department(deptName, permID) VALUES ('IT', 1);
INSERT INTO tracker.dbo.department(deptName, permID) VALUES ('Землеустройство', 1);
INSERT INTO tracker.dbo.department(deptName, permID) VALUES ('Геология', 1);
INSERT INTO tracker.dbo.department(deptName, permID) VALUES ('Геодезия', 1);

INSERT INTO tracker.dbo.employee(lastName, firstName, surName, jobTitle, login, pass, deptID, roleID, permID) 
VALUES ('Шмойлова', 'Ксения', 'Владимировна', 'Администратор', 'Ksiona','45B6426812E100EF0E6F16B7E49DB39D' ,10, 1, 1);

SELECT * FROM tracker.dbo.employee;
SELECT * FROM tracker.dbo.department;
SELECT * FROM tracker.dbo.permission;
SELECT * FROM tracker.dbo.permission_group;
SELECT * FROM tracker.dbo.role;