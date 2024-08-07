package com.oneux.iam.role.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneux.iam.department.entity.Department;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.role.entity.Role;
import com.oneux.iam.role.repository.RoleRepository;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class RoleService {
private final RoleRepository roleRepository;

public RoleService(RoleRepository roleRepository) {
	super();
	this.roleRepository = roleRepository;
}

public List<Role>getAllRoles() throws IAMException{
log.info("******RoleService.getAllRoles**************");
	
	List<Role>role=new ArrayList<>();
	try {
		role=roleRepository.findByStatus("Active");
		
		
if(role.isEmpty()) {
    	 throw new IAMException("R001","MessageConstants.BAD_REQUEST1");
     }
	}catch(Exception e) {
		log.error("******RoleService.getAllRoles,server read db exception****" + e); 
		List<String> errors = new ArrayList<>();
		throw new IAMException("R002", 
			 MessageConstants.SERVER_ERROR);
	
	}
	
	return role;
}

	
	

public Optional<Role>getRoleById(Long roleId) throws IAMException{
	
	log.info("************RoleService.getRoleById************");
	try {
	Optional<Role> role = roleRepository.findById(roleId);
	if (role.isEmpty()) {
		throw new IAMException("R003","MessageConstants.NOT_FOUND");
	}
	return role;
	}catch(IllegalArgumentException e) {
		throw new IAMException("R004","MessageConstants.EMPTY");
	
	}catch(Exception e) {
		log.info("*********RoleService.getRoleById,no such id exists");
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("R005",MessageConstants.SERVER_ERROR);
	}
}

public Role createRole(Role role) throws IAMException {
	
	log.info("*************RoleService.createRole***********");
	try {
		if(role.getRoleName().isEmpty()||role.getRoleName().length()==0) {
			throw new IAMException("R006",MessageConstants.BAD_REQUEST);
		}
		Role savedRole=roleRepository.save(role);
		return savedRole;
	}catch(IllegalArgumentException e) {
		throw new IAMException("R007","MessageConstants.ERROR"+e.getMessage());
	}catch(Exception e) {
		throw new IAMException("R008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
	}
}

public Role updateRole(Long roleId,Role role) throws IAMException {
	log.info("**************roleService.updateRole*******************");
	 
    if (roleId == null || roleId <= 0) {
        throw new IAMException("R009","MessageConstants.BAD_REQUEST");
    }
    if (role == null) {
        throw new IAMException("R0010","MessageConstants.NO_CONTENT");
    }
    if (role.getRoleName() == null || role.getRoleName().isEmpty()) {
        throw new IAMException("R0011","MessageConstants.ERROR");
    }

   Role existingRole = roleRepository.findById(roleId)
           .orElseThrow(() -> new IAMException("Org0012","MessageConstants.ERROR1"));
    try {
    existingRole.setRoleName(role.getRoleName());
    existingRole.setStatus(role.getStatus());
    existingRole.setDescription(role.getDescription());
    
    return roleRepository.save(existingRole);
    	
} catch (Exception e) {
    log.info("****************RoleService.updateRole,server read db exception*************");
    List<String> errors = new ArrayList<>();
	errors.add(e.getMessage());
    throw new IAMException("R0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
}

	}

public Role deleteRole(Long roleId) throws IAMException {
	log.info("**********RoleService.deleteByRoleId**************");
	
	Optional<Role> optionalRole = roleRepository.findById(roleId);

	
	if(optionalRole==null) {
		throw new IAMException("R0014","MessageConstants.NOT_FOUND");
	}
	try {
	if (optionalRole.isPresent()) {
		Role role = optionalRole.get();
		role.setStatus("InActive");
	  return  roleRepository.save(role);
	     
	}		 
}catch(Exception e){
	List<String> errors = new ArrayList<>();
	errors.add(e.getMessage());
	throw new IAMException("R0015","MessageConstants.SERVER_ERROR"+e.getMessage());
	}
 return roleRepository.save(deleteRole(roleId)); 
}

}
	



