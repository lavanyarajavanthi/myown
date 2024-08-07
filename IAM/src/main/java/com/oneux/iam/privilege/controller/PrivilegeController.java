package com.oneux.iam.privilege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneux.iam.constants.IAMConstants;
import com.oneux.iam.department.service.DepartmentService;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.privilege.entity.Privilege;
import com.oneux.iam.privilege.service.PrivilegeService;
import com.oneux.iam.success.ApiSuccess;
@RestController
@RequestMapping(IAMConstants.PRIVILEGE_CONTEXT)
public class PrivilegeController {
	private final PrivilegeService privilegeService;

	@Autowired

	public PrivilegeController(PrivilegeService privilegeService) {
		super();
		this.privilegeService = privilegeService;
	}

	
	@GetMapping(value = IAMConstants.GET_ALL_PRIVILEGES, produces = "application/json")
	public List<Privilege> getAllPrivilegess() throws IAMException {
		return privilegeService.getAllPrivileges();
	}

	@GetMapping(value = IAMConstants.GET_PRIVILEGE_BY_ID, produces = "application/json")
	public ResponseEntity<Privilege> getPrivilegeById(@PathVariable int privilegeId) throws IAMException {
		privilegeService.getPrivilegeById(privilegeId);
		return new ResponseEntity<Privilege>(HttpStatus.OK);
	}

	@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
	public Privilege createPrivilege(@RequestBody Privilege privilege) throws IAMException {
		return privilegeService.createPrivilege(privilege);
	}

	@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
	public Privilege updatePrivilege(@RequestBody int privilegeid, Privilege privilege) throws IAMException {
		return privilegeService.updatePrivilege(privilegeid,privilege);
	}

	@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
	public ApiSuccess deletePrivilege(@PathVariable int privilegeId) throws IAMException {
		privilegeService.deletePrivilege(privilegeId);
		return new ApiSuccess(HttpStatus.OK, "successfully deleted");
	}
}


