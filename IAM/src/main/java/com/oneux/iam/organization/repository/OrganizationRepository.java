package com.oneux.iam.organization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.organization.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Organization orgId = null;

	List<Organization> findByStatus(String status);

}
