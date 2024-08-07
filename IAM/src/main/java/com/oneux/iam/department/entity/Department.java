package com.oneux.iam.department.entity;

import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.role.entity.Role;
import com.oneux.iam.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="deptid")
	private Long deptId;

	@Column(name = "deptname", nullable = false)
	private String deptName;
	
	@Column(name="status")
	private String status;
	
	
	/*@OneToOne
	@JoinColumn(name = "userid", nullable = false)
	private User user;
	
	@OneToMany
	@JoinColumn(name="roleid",nullable=false)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="orgid",nullable=false)
	private Organization organization;*/
	
	
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/*public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}*/
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/*public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}*/
	

}
