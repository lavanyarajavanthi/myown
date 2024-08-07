package com.oneux.iam.role.entity;

import com.oneux.iam.department.entity.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Role {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="roleid")	
private Long roleId;

@Column(name="rolename")
private String roleName;

@Column(name="description")
private String description;

@Column(name="status",nullable=false)
private String status;
/*@ManyToOne
@JoinColumn(name="deptid",nullable=false)
private Department department;*/

public Long getRoleId() {
	return roleId;
}

public void setRoleId(Long roleId) {
	this.roleId = roleId;
}

public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}


}
