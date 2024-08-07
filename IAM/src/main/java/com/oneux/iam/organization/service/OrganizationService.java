/*
********************************************************************************************************************************
Class Name: OrganizationController
Description: where you perform business logic for Organization class
Author: Lavanya R
Date Created: 05-July-2024
********************************************************************************************************************************
*/
package com.oneux.iam.organization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.organization.repository.OrganizationRepository;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrganizationService {

	private final OrganizationRepository organizationRepository;

	@Autowired
	public OrganizationService(OrganizationRepository organizationRepository) {
		this.organizationRepository = organizationRepository;
	}


	public List<Organization> getAllOrganizations() throws IAMException {
		log.info("******OrganizationService.getAllOrganizations**************");
		//ApiSuccess apisuccess=new ApiSuccess();
		List<Organization>organization=new ArrayList<>();
		try {
			organization=organizationRepository.findByStatus("Active");
			
			//List<String> errors = null;
			//throw new OrganizationServiceException("Org001", errors,
					// OrganizationConstants.SERVER_ERROR);
    if(organization.isEmpty()) {
	    	 throw new IAMException("Org001","MessageConstants.BAD_REQUEST1");
	     }
		}catch(Exception e) {
			log.error("******OrganizationService.getAllOrganizations,server read db exception****" + e); 
			List<String> errors = new ArrayList<>();
			throw new IAMException("Org002", 
				 MessageConstants.SERVER_ERROR);
		
		}
		
		return organization;
	}

	public Optional<Organization> getOrganizationById(Long orgId) throws IAMException { 
		log.info("************OrganizationService.getOtganizationById************");
		try {
		Optional<Organization> organization = organizationRepository.findById(orgId);
		if (organization.isEmpty()) {
			throw new IAMException("Org003","MessageConstants.NOT_FOUND");
		}
		return organization;
		}catch(IllegalArgumentException e) {
			throw new IAMException("0rg004","MessageConstants.EMPTY");
		
		}catch(Exception e) {
			log.info("*********OrganizationService.getOrganizationById,no such id exists");
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			throw new IAMException("Org005",MessageConstants.SERVER_ERROR);
		}
		
	}

	public Organization createOrganization(Organization organization) throws IAMException {
		log.info("*************OrganizationService.createOrganization***********");
		try {
			if(organization.getOrgName().isEmpty()||organization.getOrgName().length()==0) {
				throw new IAMException("Org006",MessageConstants.BAD_REQUEST);
			}
			Organization savedOrganization=organizationRepository.save(organization);
			return savedOrganization;
		}catch(IllegalArgumentException e) {
			throw new IAMException("Org007","MessageConstants.ERROR"+e.getMessage());
		}catch(Exception e) {
			throw new IAMException("Org008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
		}
	}
	
	

	public Organization updateOrganization(Long orgId,Organization organization) throws IAMException {
		log.info("**************OrganizationService.updateOrgnization*******************");
		 
	            if (orgId == null || orgId <= 0) {
	                throw new IAMException("Org009","MessageConstants.BAD_REQUEST");
	            }
	            if (organization == null) {
	                throw new IAMException("Org0010","MessageConstants.NO_CONTENT");
	            }
	            if (organization.getOrgName() == null || organization.getOrgName().isEmpty()) {
	                throw new IAMException("Org0011","MessageConstants.ERROR");
	            }

	           Organization existingOrganization = organizationRepository.findById(orgId)
	                   .orElseThrow(() -> new IAMException("Org0012","MessageConstants.ERROR1"));
	            try {
	            existingOrganization.setOrgName(organization.getOrgName());
	            existingOrganization.setOrgCode(organization.getOrgCode());
	            existingOrganization.setOfficeAddressLine1(organization.getOfficeAddressLine1());
	    		existingOrganization.setOfficeAddressLine2(organization.getOfficeAddressLine2());
	    		existingOrganization.setOfficeContactPerson(organization.getOfficeContactPerson());
	    		existingOrganization.setOfficeContactNo(organization.getOfficeContactNo());
	    		existingOrganization.setOfficeEmail(organization.getOfficeEmail());

	            return organizationRepository.save(existingOrganization);
	            	
	        } catch (Exception e) {
	            log.error("****************OrganizationService.updateOrganization,server read db exception*************");
	            List<String> errors = new ArrayList<>();
				errors.add(e.getMessage());
	            throw new IAMException("Org0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
	        }
	}
	    
	
	

	public Organization deleteOrganization(Long orgId) throws IAMException {
		log.info("**********OrganizationService.deleteByOganizationId**************");
	
		Optional<Organization> optionalOrganization = organizationRepository.findById(orgId);
	
		
		if(optionalOrganization==null) {
			throw new IAMException("Org0014","MessageConstants.NOT_FOUND");
		}
		try {
		if (optionalOrganization.isPresent()) {
			Organization organization = optionalOrganization.get();
			organization.setStatus("InActive");
			return organizationRepository.save(organization);
			  
		}
	}catch(Exception e){
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("0rg0015","MessageConstants.SERVER_ERROR"+e.getMessage());
		}
	return organizationRepository.save(deleteOrganization(orgId)); 
	}
	}
	