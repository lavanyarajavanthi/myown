package com.oneux.iam.password.controller;

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
import com.oneux.iam.password.entity.Password;
import com.oneux.iam.password.service.PasswordService;
import com.oneux.iam.success.ApiSuccess;

@RestController
@RequestMapping(IAMConstants.PASSWORD_CONTEXT)
public class PasswordController {
private final PasswordService passwordService;
@Autowired
public PasswordController(PasswordService passwordService) {
	super();
	this.passwordService = passwordService;
}

@GetMapping(value = IAMConstants.GET_PASSWORD_BY_ID, produces = "application/json")
public ResponseEntity<Password> getPasswordById(@PathVariable Long userId) throws IAMException{
	passwordService.getPasswordById(userId);
	return new ResponseEntity<Password>(HttpStatus.OK);
}

@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
public Password createPassword(@RequestBody Password password) {
	return passwordService.createPassword(password);
}

@PostMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
public Password updatePassword(@RequestBody Password password) {
	return passwordService.updatePassword(password);
}

@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
public ApiSuccess deletePassword(@PathVariable Long userId) throws IAMException {
	passwordService.deletePassword(userId);
	return new ApiSuccess(HttpStatus.OK, "successfully deleted");
}
}




