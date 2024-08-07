package com.oneux.iam.privilege.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.privilege.entity.Privilege;
import com.oneux.iam.privilege.repository.PrivilegeRepository;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class PrivilegeService {
	
	private final PrivilegeRepository privilegeRepository;
	
	@Autowired
	public PrivilegeService(PrivilegeRepository privilegeRepository) {
		super();
		this.privilegeRepository = privilegeRepository;
	}

	public List<Privilege>getAllPrivileges() throws IAMException{
		log.info("******PrivilegeService.getAllPrivileges**************");
		
		List<Privilege>privilege=new ArrayList<>();
		try {
			privilege=privilegeRepository.findByStatus("Active");
			
			//List<String> errors = null;
			//throw new OrganizationServiceException("Org001", errors,
					// OrganizationConstants.SERVER_ERROR);
    if(privilege.isEmpty()) {
	    	 throw new IAMException("P001","MessageConstants.BAD_REQUEST1");
	     }
		}catch(Exception e) {
			log.error("******PrivilegeService.getAllPrivileges,server read db exception****" + e); 
			List<String> errors = new ArrayList<>();
			throw new IAMException("P002", 
				 MessageConstants.SERVER_ERROR);
		
		}
		
		return privilege;
	}


	public Optional<Privilege>getPrivilegeById(int privilegeid) throws IAMException{
		log.info("************PrivilegeService.getPrivilegeById************");
		try {
		Optional<Privilege> privilege = privilegeRepository.findById(privilegeid);
		if (privilege.isEmpty()) {
			throw new IAMException("P003","MessageConstants.NOT_FOUND");
		}
		return privilege;
		}catch(IllegalArgumentException e) {
			throw new IAMException("P004","MessageConstants.EMPTY");
		
		}catch(Exception e) {
			log.info("*********OrganizationService.getOrganizationById,no such id exists");
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			throw new IAMException("P005",MessageConstants.SERVER_ERROR);
		}
		
	}

	public Privilege createPrivilege(Privilege privilege) throws IAMException {
		
		log.info("*************PrivilegeService.createPrivilege***********");
		try {
			if(privilege.getPrivilegeName().isEmpty()||privilege.getPrivilegeId()==0) {
				throw new IAMException("P006",MessageConstants.BAD_REQUEST);
			}
			Privilege savedprivilege=privilegeRepository.save(privilege);
			return savedprivilege;
		}catch(IllegalArgumentException e) {
			throw new IAMException("P007","MessageConstants.ERROR"+e.getMessage());
		}catch(Exception e) {
			throw new IAMException("P008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
		}
	}

	public Privilege updatePrivilege(int privilegeId,Privilege privilege) throws IAMException {
		log.info("**************PrivilegeService.updatePrivilege*******************");
		 
        if (privilegeId == 0 ) {
            throw new IAMException("P009","MessageConstants.BAD_REQUEST");
        }
        if (privilege == null) {
            throw new IAMException("P0010","MessageConstants.NO_CONTENT");
        }
        if (privilege.getPrivilegeName() == null || privilege.getPrivilegeName().isEmpty()) {
            throw new IAMException("P0011","MessageConstants.ERROR");
        }

       Privilege existingprivilege = privilegeRepository.findById(privilegeId)
               .orElseThrow(() -> new IAMException("Org0012","MessageConstants.ERROR1"));
        try {
        	existingprivilege.setPrivilegeId(privilege.getPrivilegeId());
    		existingprivilege.setPrivilegeName(privilege.getPrivilegeName());
    		existingprivilege.setDescription(privilege.getDescription());
    			return privilegeRepository.save(privilege);
    } catch (Exception e) {
        log.info("****************PrivilegeService.updatePrivilege,server read db exception*************");
        List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
        throw new IAMException("P0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
    }

}


	public Privilege deletePrivilege(int privilegeid) throws IAMException {
		log.info("**********PrivilegeService.deleteByPrivilegeId**************");
		
		Optional<Privilege> optionalprivilege = privilegeRepository.findById(privilegeid);
	
		
		if(optionalprivilege==null) {
			throw new IAMException("P0014","MessageConstants.NOT_FOUND");
		}
		try {
		if (optionalprivilege.isPresent()) {
			Privilege privilege = optionalprivilege.get();
			privilege.setStatus("InActive");
			return privilegeRepository.save(privilege);
			  
		}
	}catch(Exception e){
		List<String> errors = new ArrayList<>();
		errors.add(e.getMessage());
		throw new IAMException("P0015","MessageConstants.SERVER_ERROR"+e.getMessage());
		}
	return privilegeRepository.save(deletePrivilege(privilegeid)); 
	}
}







