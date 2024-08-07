package com.oneux.iam.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

  List<Department> findByStatus(String status);
		
}
