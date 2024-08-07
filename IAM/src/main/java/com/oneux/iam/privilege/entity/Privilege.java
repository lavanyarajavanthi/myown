package com.oneux.iam.privilege.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Privilege")
public class Privilege {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)

@Column(name="privilegeid")
private int privilegeId;

@Column(name="privilegename")
private String privilegeName;

@Column(name="description")
private String description;

@Column(name="status",nullable=false)
private String status;

public int getPrivilegeId() {
	return privilegeId;
}

public void setPrivilegeId(int privilegeId) {
	this.privilegeId = privilegeId;
}

public String getPrivilegeName() {
	return privilegeName;
}

public void setPrivilegeName(String privilegeName) {
	this.privilegeName = privilegeName;
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
