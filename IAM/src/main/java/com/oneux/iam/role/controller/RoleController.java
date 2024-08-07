package com.oneux.iam.role.controller;

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
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.role.entity.Role;
import com.oneux.iam.role.service.RoleService;
import com.oneux.iam.success.ApiSuccess;

@RestController
@RequestMapping(IAMConstants.ROLE_CONTEXT)
public class RoleController {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@GetMapping(value = IAMConstants.GET_ALL_ROLES, produces = "application/json")
	public List<Role> getAllRoles() throws IAMException {
		return roleService.getAllRoles();
	}

	@GetMapping(value = IAMConstants.GET_ROLE_BY_ID, produces = "application/json")
	public ResponseEntity<Role> getRoleById(@PathVariable Long roleId) throws IAMException {
		roleService.getRoleById(roleId);
		return new ResponseEntity<Role>(HttpStatus.OK);
	}

	@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
	public Role createRole(@RequestBody Role role) throws IAMException {
		return roleService.createRole(role);
	}

	@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
	public Role updateRole(@RequestBody Long roleId, Role role) throws IAMException {
		return roleService.updateRole(roleId,role);
	}

	@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
	public ApiSuccess deleteRole(@PathVariable Long roleId) throws IAMException {
		roleService.deleteRole(roleId);
		return new ApiSuccess(HttpStatus.OK, "successfully deleted");
	}
}
