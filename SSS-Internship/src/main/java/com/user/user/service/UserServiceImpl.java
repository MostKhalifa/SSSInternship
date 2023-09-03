package com.user.user.service;

import java.util.HashSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.user.dto.request.Login;
import com.user.user.dto.request.RoleEdit;
import com.user.user.dto.request.Signup;
import com.user.user.dto.request.UsernameEdit;
import com.user.user.dto.response.JwtResponse;
import com.user.user.dto.response.MsgResponse;
import com.user.user.dto.response.UserGetDto;
import com.user.user.entity.*;
import com.user.user.exceptions.*;
import com.user.user.mapper.IUserMapper;
import com.user.user.repository.RoleRepo;
import com.user.user.repository.UserRepo;
import com.user.user.security.jwt.JwtUtils;
import com.user.user.security.service.UserDetailsImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@CacheConfig(cacheNames = { "User" })
public class UserServiceImpl implements IUserService {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private IUserMapper userMapper;

	@Autowired
	private RedisTemplate template;
	

//	@Autowired
//	private RestTemplate restTemplate;

	@CacheEvict(key = "#loginRequest.username", value = "User")
	public JwtResponse authenticateUser(Login loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
	}

	public void registerUser(Signup signUpRequest) {
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		System.out.println(strRoles);
		if (strRoles == null) {
			Role userRole = roleRepo.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found1."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found2."));
					roles.add(adminRole);
					break;
				case "mod":
					Role modRole = roleRepo.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found3."));
					roles.add(modRole);
					break;
				default:
					Role userRole = roleRepo.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found4."));
					roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userRepo.save(user);
	}

	@Override
	@CachePut(key = "#username", value = "User")
	public void editRole(String username, RoleEdit roleEdit) throws Exception {
		User user = userRepo.findByUsername(username);
		Role role;

		if (user == null) {
			throw new Exception("Please enter a valid username");
		}

		if (roleEdit.getRole().equals("admin")) {
			role = roleRepo.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found2."));
		} else if (roleEdit.getRole().equals("mod")) {
			role = roleRepo.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found3."));
		}

		else {
			throw new Exception("Please enter a valid role to delete");
		}
		user.getRoles().remove(role);
		userRepo.save(user);

	}

	public UserGetDto getUser(Long id) {
		return userMapper.entityToDto(userRepo.findById(id).get());
	}

	public List<UserGetDto> getAllUsers() {
		return userMapper.entityToDtos(userRepo.findAll());
	}

	public void updateUsername(Long id, UsernameEdit username) {
		User user = userRepo.findById(id).get();
		if (user == null || username == null) {
			return;
		}
		String newName = username.getUsername();
		user.setUsername(newName);
		userRepo.save(user);
	}

	public void deleteUser(Long id) {
		UserGetDto userGetDto = userMapper.entityToDto(userRepo.findById(id).get());
		userRepo.deleteById(userGetDto.getId());
	}

//	public UserOrderDto getUserOrders(Long customerId){
//		String URL = "http://orders/orders/customer/{customerId}";
//		UserGetDto userGet = userMapper.entityToDto(userRepo.findById(customerId).get());
//		UserOrderDto data =userMapper.userGetDtoToUserOrderDto(userGet);
//		data.setOrder(restTemplate.getForObject(URL,List.class,customerId));
//		return data;
//	}

}
