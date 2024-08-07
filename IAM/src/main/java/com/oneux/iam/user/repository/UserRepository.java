package com.oneux.iam.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oneux.iam.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByStatus(String status);

}
