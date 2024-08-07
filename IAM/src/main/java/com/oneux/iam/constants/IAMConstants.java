package com.oneux.iam.constants;

public interface IAMConstants {


	/* User  Services*/
	
	String USER_CONTEXT="users";
	String GET_ALL_USERS="find";
	String GET_USER_BY_ID="getUserById";
	
	

	/*Organization Services*/
	
	String ORGANIZATION_CONTEXT="organizations";
	String GET_ALL_ORGANIZATIONS="find";
	String GET_ORGANIZATION_BY_ID="getOrganizationById";
	
	

	/*Role services*/
	
	String ROLE_CONTEXT="roles";
	String GET_ALL_ROLES = "find";
	String GET_ROLE_BY_ID = "getRoleById";
	
	/*Department Services*/
	
	String DEPARTMENT_CONTEXT="departments";
	String GET_DEPARTMENT_BY_ID = "getDepartmentById";
	String GET_ALL_DEPARTMENTS = "find";
	
	
	/*UserRole services*/
	
	String USERROLE_CONTEXT="userroles";
	String GET_USERROLE_BY_ID = "getUserroleById";
	String GET_ALL_USERROLES = "find";

	
	/*Privilege Services*/
	
	String PRIVILEGE_CONTEXT="privileges";
	String GET_ALL_PRIVILEGES = "find";
	String GET_PRIVILEGE_BY_ID="getPrivilegeById";
	
	/*Password Services*/
	
	String PASSWORD_CONTEXT="passwords";
	String GET_PASSWORD_BY_ID = "getPasswordById";

	
	
	/*common operations*/
	String CREATE="create";
	String UPDATE_BY_ID="update";
	String DELETE_BY_ID="delete";
	
	
}
