/*
********************************************************************************************************************************
Class Name: OrganizationService
Description: where you perform business logic for User class
Author: Lavanya R
Date Created: 05-July-2024
********************************************************************************************************************************
*/
package com.oneux.iam.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.user.entity.User;
import com.oneux.iam.user.repository.UserRepository;
import com.oneux.iam.util.MessageConstants;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	public List<User> getAllUsers() throws IAMException {
		log.info("**********UserService.getAllUsers*********");
		List<User>user=new ArrayList<>();
		try {
			 user=userRepository.findByStatus("Active");
			if(user.isEmpty()) {
				throw new IAMException("U001","MessageConstants.BAD_REQUEST1");
				}
		
		}catch(Exception e){
			log.info("*********UserService.getAllUsers,server read db exception***********");
			List<String> errors = new ArrayList<>();
			
			throw new IAMException("U002","MessageConstants.SERVER_ERROR");
		}
		return user;
	}
	
	public Optional<User> getUserById(Long userId) throws IAMException {
		log.info("*********UserService.getUserById**********");
		try {
		 Optional<User> user= userRepository.findById(userId);
		 if(user.isEmpty()) {
			 throw new IAMException("U003","MessageConstants.NOT_FOUND");
		}
		return user;
		}catch(IllegalArgumentException e) {
			throw new IAMException("U004","MessageConstants.EMPTY");
		
		}catch(Exception e) {
			log.info("*********UserService.getUserById,no such id exists");
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			throw new IAMException("U005",MessageConstants.SERVER_ERROR);
		}
		
	}


	public User createUser(User user) throws IAMException{
		log.info("************UserService.createUser***********");
		
			try {
				if(	user.getUserName().isEmpty()||user.getFirstName().isEmpty()||user.getLastName().isEmpty()){ 
					throw new IAMException("U006",MessageConstants.BAD_REQUEST);
				}
				User savedUser=userRepository.save(user);
				return savedUser;
			}catch(IllegalArgumentException e) {
				throw new IAMException("U007","MessageConstants.ERROR"+e.getMessage());
			}catch(Exception e) {
				throw new IAMException("U008","MessageConstants.INTERNAL_ERROR"+e.getMessage());
			}
		}

		

	public User updateUser(Long userId ,User user) throws IAMException {
		/*Optional<User> existingUser = userRepository.findById(user.getUserId());
		if (existingUser != null) {
			User existingUser1 = user;
			
		} else {
			throw new IAMException("user doesn't exist:" + user,"user not found");
		}
	}*/
	log.info("**************UserService.updateUser*******************");
	 try {
           if (userId == null || userId <= 0) {
               throw new IAMException("U009","MessageConstants.BAD_REQUEST");
           }
           if (userId == null) {
               throw new IAMException("U0010","MessageConstants.NO_CONTENT");
           }
           if (user.getUserName() == null || user.getFirstName().isEmpty() ||user.getLastName().isEmpty()) {
               throw new IAMException("U0011","MessageConstants.ERROR");
           }

          User existingUser = userRepository.findById(userId)
                  .orElseThrow(() -> new IAMException("U0012","MessageConstants.ERROR1"));
           
          existingUser.setFirstName(user.getFirstName());
			existingUser.setLastName(user.getLastName());
			existingUser.setUserName(user.getUserName());
			existingUser.setEmail(user.getEmail());
			existingUser.setContactNo(user.getContactNo());
			existingUser.setAddressLine1(user.getAddressLine1());
			existingUser.setAddressLine2(user.getAddressLine2());
			existingUser.setOrganization(user.getOrganization());
			existingUser.setDepartment(user.getDepartment());
			return userRepository.save(existingUser);
        
       } catch (IAMException e) {
           log.info("****************UserService.updateOrganization,server read db exception*************");
           List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
           throw new IAMException("U0013","MessageConstants.SERVER_ERROR"+ e.getMessage());
       }

   }





	public User deleteUser(Long userId) throws IAMException {
		log.info("**********OrganizationService.deleteByOganizationId**************");
		try {
			Optional<User> optionalUser = userRepository.findById(userId);
		
			
			if(optionalUser==null) {
				throw new IAMException("U0014","MessageConstants.NOT_FOUND");
			}
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				user.setStatus("InActive");
				return userRepository.save(user);
				  
			}
		}catch(Exception e){
			log.info("*******UserService.deleteById,server read db exception");
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			throw new IAMException("U0015","MessageConstants.SERVER_ERROR"+e.getMessage());
			}
		return userRepository.save(deleteUser(userId)); 
		}
}
		

