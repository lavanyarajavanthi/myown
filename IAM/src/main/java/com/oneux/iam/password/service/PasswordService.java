package com.oneux.iam.password.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.oneux.iam.exceptions.IAMException;
import com.oneux.iam.password.entity.Password;
import com.oneux.iam.password.repository.PasswordRepository;
import com.oneux.iam.success.ApiSuccess;
import com.oneux.iam.user.entity.User;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class PasswordService {
	
private final PasswordRepository passwordRepository;
@Autowired
public PasswordService(PasswordRepository passwordRepository) {
	super();
	this.passwordRepository = passwordRepository;
}

public Optional<Password> getPasswordById(Long userId) throws IAMException {
	Optional<Password> password = passwordRepository.findById(userId);
	if (password != null) {
		return password;
	} else {
		throw new IAMException("user not found with id:" + userId,"user not found");
	}
}

public Password createPassword(Password password) {
	return passwordRepository.save(password);
}

public Password updatePassword(Password password) {
	Password existingpassword = passwordRepository.findById(password.getUserId()).orElseThrow(null);
	existingpassword.setUserId(password.getUserId());
	existingpassword.setPassword(password.getPassword());
	existingpassword.setCreatedDate(password.getCreatedDate());
	existingpassword.setCreatedBy(password.getCreatedBy());
	return passwordRepository.save(password);
}

public ApiSuccess deletePassword(Long userId) throws IAMException {
	Optional<Password> optionalpassword = passwordRepository.findById(userId);
	if (optionalpassword.isPresent()) {
	Password pswd = optionalpassword.get();
		pswd.setStatus("InActive");
		passwordRepository.save(pswd);
		return new ApiSuccess(HttpStatus.OK, "successfully deleted");
	} else {
		throw new IAMException("User not found with id: " + userId,"user not found");
	}
}

}
