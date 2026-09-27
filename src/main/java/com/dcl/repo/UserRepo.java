package com.dcl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dcl.entity.User;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	
	@Query("SELECT u FROM User u WHERE u.email=:email")
	Optional<User> findByEmail(String email);
}
