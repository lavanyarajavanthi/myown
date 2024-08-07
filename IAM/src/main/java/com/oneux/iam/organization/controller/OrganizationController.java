
/*
********************************************************************************************************************************
Class Name: OrganizationController
Description:It is responsible for handling incoming HTTP requests and returning an appropriate response for Organization 
Author: Lavanya R
Date Created: 05-July-2024
********************************************************************************************************************************
*/
package com.oneux.iam.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.oneux.iam.constants.IAMConstants;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.organization.service.OrganizationService;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.user.entity.User;


import java.util.List;

@RestController
@RequestMapping(IAMConstants.ORGANIZATION_CONTEXT)
public class OrganizationController {

	private final OrganizationService organizationService;

	@Autowired
	public OrganizationController(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	@GetMapping(value = IAMConstants.GET_ALL_ORGANIZATIONS, produces = "application/json")

	public List<Organization> getAllOrganizations() throws IAMException {
		return organizationService.getAllOrganizations();
	}
	
	@GetMapping(value = IAMConstants.GET_ORGANIZATION_BY_ID, produces = "application/json")
	public ResponseEntity<Organization> getOrganizationById(@PathVariable Long orgId) throws  IAMException{
		organizationService.getOrganizationById(orgId);
		return new ResponseEntity<Organization>(HttpStatus.OK);
	}

	@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
	public Organization createOrganization(@RequestBody Organization organization) throws IAMException {
		return organizationService.createOrganization(organization);
	}

	@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
	public Organization updateOrganization(@RequestBody Long orgid, Organization organization) throws IAMException {
		return organizationService.updateOrganization(orgid,organization);
	}

	@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
	public ApiSuccess deleteOrganization(@PathVariable Long orgId) throws IAMException {

		organizationService.deleteOrganization(orgId);
		return new ApiSuccess(HttpStatus.OK, "successfully deleted");
	}
}
