package com.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.user.user.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	User findByEmail(@Param("email") String email);
	User findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	//@Modifying
	//@Query(“UPDATE User u WHERE c.user = :id”)
	//void updateUserById(@Param("id") long id);

}
