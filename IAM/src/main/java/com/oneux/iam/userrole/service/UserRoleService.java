package com.oneux.iam.userrole.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.userrole.entity.UserRole;
import com.oneux.iam.userrole.repository.UserRoleRepository;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class UserRoleService {
	private final UserRoleRepository userRoleRepository;

	public UserRoleService(UserRoleRepository userRoleRepository) {
		super();
		this.userRoleRepository = userRoleRepository;
	}

	public List<UserRole> getAllUserRoles() throws IAMException {
		log.info("******UserRoleService.getAllUserRoles**************");
		List<UserRole>userrole=new ArrayList<>();
		try {
			userrole=userRoleRepository.findByStatus("Active");
			
			//List<String> errors = null;
			//throw new OrganizationServiceException("Org001", errors,
					// OrganizationConstants.SERVER_ERROR);
    if(userrole.isEmpty()) {
	    	 throw new IAMException("UR001","MessageConstants.BAD_REQUEST1");
	     }
		}catch(Exception e) {
			log.error("******UserRoleService.getAllUserRoles,server read db exception****" + e); 
			List<String> errors = new ArrayList<>();
			throw new IAMException("UR002", 
				 MessageConstants.SERVER_ERROR);
		
		}
		
		return userrole;
	}

	

	public Optional<UserRole> getUserRoleById(Long userId) throws IAMException {
		
		log.info("************UserRoleService.getUserRoleById************");
		try {
		Optional<UserRole> userrole = userRoleRepository.findById(userId);
		if (userrole.isEmpty()) {
			throw new IAMException("UR003","MessageConstants.NOT_FOUND");
		}
		return userrole;
		}catch(IllegalArgumentException e) {
			throw new IAMException("UR004","MessageConstants.EMPTY");
		
		}catch(Exception e) {
			log.info("*********UserRoleService.getUserRoleById,no such id exists");
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			throw new IAMException("UR005",MessageConstants.SERVER_ERROR);
		}
		
	}
	public UserRole createUserRole(UserRole userrole) throws IAMException {
		log.info("*************UserRoleService.createUserRole***********");
		try {
			if(userrole.getRoleId()==null||userrole.getUserId()==null||userrole.getOrgId()==null) {
				throw new IAMException("UR006",MessageConstants.BAD_REQUEST);
			}
		   UserRole saveduserrole=userRoleRepository.save(userrole);
			return saveduserrole;
		}catch(IllegalArgumentException e) {
			throw new IAMException("UR007","MessageConstants.ERROR"+e.getMessage());
		}catch(Exception e) {
			throw new IAMException("UR008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
		}
	}
	
	


	public UserRole updateUserRole( Long userId,UserRole userrole) throws IAMException {
		log.info("**************OrganizationService.updateOrgnization*******************");
		 
        if (userId == null || userId <= 0) {
            throw new IAMException("UR009","MessageConstants.BAD_REQUEST");
        }
        if (userrole == null) {
            throw new IAMException("UR0010","MessageConstants.NO_CONTENT");
        }
        if (userrole.getOrgId() == null || userrole.getRoleId()==null) {
            throw new IAMException("UR0011","MessageConstants.ERROR");
        }

       UserRole existinguserrole = userRoleRepository.findById(userId)
               .orElseThrow(() -> new IAMException("UR0012","MessageConstants.ERROR1"));
        try {
        	existinguserrole.setOrgId(userrole.getOrgId());
    		existinguserrole.setRoleId(userrole.getRoleId());
    		existinguserrole.setStatus(userrole.getStatus());
    		return userRoleRepository.save(userrole);
       
        	
    } catch (Exception e) {
        log.info("****************UserRoleService.updateUserRole,server read db exception*************");
        List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
        throw new IAMException("UR0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
    }

}


	public UserRole deleteUserRole(Long userId) throws IAMException {
		log.info("**********UserRoleService.deleteByUserId**************");
		
		Optional<UserRole> optionaluserrole = userRoleRepository.findById(userId);
	
		
		if(optionaluserrole==null) {
			throw new IAMException("UR0014","MessageConstants.NOT_FOUND");
		}
		try {
		if (optionaluserrole.isPresent()) {
			UserRole userrole = optionaluserrole.get();
			userrole.setStatus("InActive");
			return userRoleRepository.save(userrole);
			  
		}
	}catch(Exception e){
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("UR0015","MessageConstants.SERVER_ERROR"+e.getMessage());
		}
	return userRoleRepository.save(deleteUserRole(userId)); 
	}
}

	
		/*Optional<UserRole> optionalUserRole = userRoleRepository.findById(userId);
		if (optionalUserRole.isPresent()) {
			UserRole userrole = optionalUserRole.get();
			userrole.setStatus("InActive");
			userRoleRepository.save(userrole);
			return new ApiSuccess(HttpStatus.OK, "successfully deleted");
		} else {
			throw new IAMException("User not found with id: " + userId,"user not found");
		}
	}
}*/


