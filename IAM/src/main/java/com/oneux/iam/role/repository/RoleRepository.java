package com.oneux.iam.role.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.role.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

	List<Role> findByStatus(String status);

	

	Optional<Role> findById(Long roleid);



	Role save(Role role);

}
