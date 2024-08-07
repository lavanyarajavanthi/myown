package com.oneux.iam.userrole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.userrole.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	List<UserRole> findByStatus(String status);

}
