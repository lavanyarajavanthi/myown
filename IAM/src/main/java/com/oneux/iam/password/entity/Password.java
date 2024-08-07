package com.oneux.iam.password.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="Password")
public class Password {
@Id
@Column(name="userid")
private Long userId;

@Column(name="password")
private String password;

@Column(name="createddate")
@Temporal(TemporalType.TIMESTAMP)
private Date createdDate;

@Column(name="createdby")
private String createdBy;

@Column(name="status")
private String status;

public Long getUserId() {
	return userId;
}

public void setUserId(Long userId) {
	this.userId = userId;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Date getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Date createdDate) {
	this.createdDate = createdDate;
}

public String getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

}
