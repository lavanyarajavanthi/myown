package com.oneux.iam.userrole.controller;

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
import com.oneux.iam.organization.entity.Organization;
import com.oneux.iam.organization.service.OrganizationService;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.userrole.entity.UserRole;
import com.oneux.iam.userrole.service.UserRoleService;

@RestController
@RequestMapping(IAMConstants.USERROLE_CONTEXT)
public class UserRoleController {
	
		private final UserRoleService userRoleService;

		@Autowired
		public UserRoleController(UserRoleService userRoleService) {
			super();
			this.userRoleService = userRoleService;
		}

		@GetMapping(value = IAMConstants.GET_ALL_USERROLES, produces = "application/json")
		public List<UserRole> getAllUserRoles() throws IAMException {
			return userRoleService.getAllUserRoles();
		}


		@GetMapping(value = IAMConstants.GET_USERROLE_BY_ID, produces = "application/json")
		public ResponseEntity<UserRole> getUserRoleById(@PathVariable Long userId) throws  IAMException{
			userRoleService.getUserRoleById(userId);
			return new ResponseEntity<UserRole>(HttpStatus.OK);
		}

		@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
		public UserRole createUserRole(@RequestBody UserRole userrole) throws IAMException {
			return userRoleService.createUserRole(userrole);
		}

		@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
		public UserRole updateUserRole(@RequestBody Long userId, UserRole userrole) throws IAMException {
			return userRoleService.updateUserRole(userId,userrole);
		}

		@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
		public ApiSuccess deleteUserRole(@PathVariable Long userId) throws IAMException {
			userRoleService.deleteUserRole(userId);
			return new ApiSuccess(HttpStatus.OK, "successfully deleted");
		}
	}


