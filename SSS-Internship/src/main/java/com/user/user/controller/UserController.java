package com.user.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user.dto.request.Login;
import com.user.user.dto.request.RoleEdit;
import com.user.user.dto.request.Signup;
import com.user.user.dto.request.UsernameEdit;
import com.user.user.dto.response.JwtResponse;
import com.user.user.dto.response.MsgResponse;
import com.user.user.dto.response.UserGetDto;
import com.user.user.entity.User;
import com.user.user.repository.UserRepo;
import com.user.user.service.IUserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
	IUserService userService;

	@Autowired
	UserRepo userRepo;

	@ApiOperation(value = "Sign in using username and password",
	        response = JwtResponse.class,
	        notes = "User must exist")
	@ApiResponse(code = 401, message = "Username or password is incorrect")
	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody Login loginRequest) {
		return ResponseEntity.ok(userService.authenticateUser(loginRequest));
	}
	
	@ApiOperation(value = "Sign up using username, email, password and roles")
	@ApiResponses(value = {
	        @ApiResponse(code = 400, message = "Error: Email or username already taken"),
	        @ApiResponse(code = 1212, message = "an error code that does not exist")})
	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Signup signUpRequest) {
		if (userRepo.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MsgResponse("Error: Username is already taken!"));
		}
		if (userRepo.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MsgResponse("Error: Email is already in use!"));
		}
		userService.registerUser(signUpRequest);
		return ResponseEntity.ok(new MsgResponse("User registered successfully!"));
	}

	@GetMapping("/test/all")
	public String allAccess() {
		log.info("Hello World");
		return "Public Content.";
	}

	@GetMapping("/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/test/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/test/user/{id}")
	@PreAuthorize("#id == authentication.principal.id")
	public ResponseEntity<UserGetDto> getUser(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
	}

	@PutMapping("/test/user/roleEdit/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MsgResponse> editUserRole(@PathVariable(value = "username") String username,
			@Valid @RequestBody RoleEdit roleEdit) throws Exception {
		userService.editRole(username, roleEdit);
		return ResponseEntity.ok(new MsgResponse("User role edited successfully!"));

	}

//	
//	@GetMapping("/user")
//	public ResponseEntity<List<UserGetDto>> getAllOrders() {
//		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
//	}
//	
	
	@PutMapping("/user/changeUsername/{id}")
	public ResponseEntity<MsgResponse> updateUser(@PathVariable Long id,@RequestBody UsernameEdit username){
		userService.updateUsername(id, username);
		return ResponseEntity.ok(new MsgResponse("Username edited successfully!"));
	}
	
	@DeleteMapping("/user/{id}")
  //@PreAuthorize("hasRole('ADMIN')")	
	public ResponseEntity<UserGetDto> deleteUser(@PathVariable(value = "id") Long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	// DO NOT DELETE : FOR REST TEMPLATE/ORDERS MICROSERVICE
//	@GetMapping("/user/{customerId}/orders")
//	public UserOrderDto getUserOrders2(@PathVariable(value = "customerId") Long customerId){
//		return userService.getUserOrders(customerId);
//	}

}
