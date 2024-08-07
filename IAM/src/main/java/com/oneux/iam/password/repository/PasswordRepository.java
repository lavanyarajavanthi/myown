package com.oneux.iam.password.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.password.entity.Password;
import com.oneux.iam.user.entity.User;

public interface PasswordRepository extends JpaRepository<Password,Long> {

	

}
