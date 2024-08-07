/*
********************************************************************************************************************************
Class Name: UserController
Description:It is responsible for handling incoming HTTP requests and returning an appropriate response for User 
Author: Lavanya R
Date Created: 05-July-2024
********************************************************************************************************************************
*/
package com.oneux.iam.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneux.iam.constants.IAMConstants;
import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.user.entity.User;
import com.oneux.iam.user.service.UserService;

@RestController

@RequestMapping(IAMConstants.USER_CONTEXT)
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = IAMConstants.GET_ALL_USERS, produces = "application/json")

	public List<User> getAllUsers() throws IAMException {
		return userService.getAllUsers();
	}


	@GetMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
	    try {
	        User user = userService.getUserById(userId).get();
	        return new ResponseEntity<>(user, HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping(value = IAMConstants.CREATE, produces = "application/json")
	public User createUser(@RequestBody User user) throws IAMException {
		return userService.createUser(user);
	}

	@PutMapping(value = IAMConstants.UPDATE_BY_ID, produces = "application/json")
	public User updateUser(@RequestBody Long userId, User user) throws IAMException {
		return userService.updateUser(userId, user);
	}

	

	@DeleteMapping(value = IAMConstants.DELETE_BY_ID, produces = "application/json")
	public ApiSuccess deleteUser(@PathVariable Long userId) throws IAMException {
		userService.deleteUser(userId);
		return new ApiSuccess(HttpStatus.OK, "successfully Deleted");
	}
}
