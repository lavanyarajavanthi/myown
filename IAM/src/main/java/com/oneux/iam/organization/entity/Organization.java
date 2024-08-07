package com.oneux.iam.organization.entity;

import java.util.Date;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.ToString;

@Entity
@Table(name = "Organization")
@ToString
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "orgid")
	private Long orgId;

	@Column(name = "orgname", nullable = false)
	private String orgName;

	@Column(name = "orgcode", nullable = false)
	private String orgCode;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "officeaddressline1")
	private String officeAddressLine1;

	@Column(name = "officeaddressline2")
	private String officeAddressLine2;

	@Column(name = "officecontactperson")
	private String officeContactPerson;

	@Column(name = "officecontactno")
	private String officeContactNo;

	@Column(name = "officeemail")
	private String officeEmail;

	@Column(name = "buildingaddressline1")
	private String buildingAddressline1;

	@Column(name = "buildingaddressline2")
	private String buildingAddressLine2;

	@Column(name = "buildingcontactperson")
	private String buildingContactPerson;

	@Column(name = "buildingcontactno")
	private String buildingContactNo;

	@Column(name = "buildingemail")
	private String buildingEmail;

	@Column(name = "createddate", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	/*@OneToMany
	@JoinColumn(name = "userid", nullable = false)
	private User user;
	@OneToMany
	@JoinColumn(name="deptid",nullable=false)
	private Department department;*/


	public Organization() {
		this.createdDate = new Date();
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOfficeAddressLine1() {
		return officeAddressLine1;
	}

	public void setOfficeAddressLine1(String officeAddressLine1) {
		this.officeAddressLine1 = officeAddressLine1;
	}

	public String getOfficeAddressLine2() {
		return officeAddressLine2;
	}

	public void setOfficeAddressLine2(String officeAddressLine2) {
		this.officeAddressLine2 = officeAddressLine2;
	}

	public String getOfficeContactPerson() {
		return officeContactPerson;
	}

	public void setOfficeContactPerson(String officeContactPerson) {
		this.officeContactPerson = officeContactPerson;
	}

	public String getOfficeContactNo() {
		return officeContactNo;
	}

	public void setOfficeContactNo(String officeContactNo) {
		this.officeContactNo = officeContactNo;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getBuildingAddressline1() {
		return buildingAddressline1;
	}

	public void setBuildingAddressline1(String buildingAddressline1) {
		this.buildingAddressline1 = buildingAddressline1;
	}

	public String getBuildingAddressLine2() {
		return buildingAddressLine2;
	}

	public void setBuildingAddressLine2(String buildingAddressLine2) {
		this.buildingAddressLine2 = buildingAddressLine2;
	}

	public String getBuildingContactPerson() {
		return buildingContactPerson;
	}

	public void setBuildingContactPerson(String buildingContactPerson) {
		this.buildingContactPerson = buildingContactPerson;
	}

	public String getBuildingContactNo() {
		return buildingContactNo;
	}

	public void setBuildingContactNo(String buildingContactNo) {
		this.buildingContactNo = buildingContactNo;
	}

	public String getBuildingEmail() {
		return buildingEmail;
	}

	public void setBuildingEmail(String buildingEmail) {
		this.buildingEmail = buildingEmail;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
