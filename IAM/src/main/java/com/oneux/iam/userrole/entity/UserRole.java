package com.oneux.iam.userrole.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserRole {
	@Id
	@Column(name="userid",nullable=false)
private Long userId;
	
@Column(name="orgid",nullable=false)
private Long orgId;

@Column(name="roleid",nullable=false)
private Long roleId;

@Column(name="status")
private String status;

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public Long getOrgId() {
	return orgId;
}

public void setOrgId(Long orgId) {
	this.orgId = orgId;
}

public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}
	
}
