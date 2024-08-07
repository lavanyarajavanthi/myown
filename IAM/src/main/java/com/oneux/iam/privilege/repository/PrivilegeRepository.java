package com.oneux.iam.privilege.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.privilege.entity.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege,Integer> {

	List<Privilege> findByStatus(String status);

}
