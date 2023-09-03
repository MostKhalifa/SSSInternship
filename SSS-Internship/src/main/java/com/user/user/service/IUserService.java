package com.user.user.service;

import java.util.List;


import org.springframework.data.jpa.repository.Modifying;

import com.user.user.dto.request.Login;
import com.user.user.dto.request.RoleEdit;
import com.user.user.dto.request.Signup;
import com.user.user.dto.request.UsernameEdit;
import com.user.user.dto.response.JwtResponse;
import com.user.user.dto.response.MsgResponse;
import com.user.user.dto.response.UserGetDto;
import com.user.user.entity.*;

public interface IUserService {


	
	JwtResponse authenticateUser(Login loginRequest);
	
	void registerUser(Signup signUpRequest);
	
//	void editRole(Long id , RoleEdit role) throws Exception;
	void editRole(String username , RoleEdit role) throws Exception;
	

	UserGetDto getUser(Long id);
	
	List<UserGetDto> getAllUsers();

	@Modifying
	void updateUsername(Long id, UsernameEdit username);

	void deleteUser(Long id);

	
	//DO NOT DELETE : FOR REST TEMPLATE/ORDERS MICROSERVICE 
//	UserOrderDto getUserOrders (Long customerId);	
	
	
}
