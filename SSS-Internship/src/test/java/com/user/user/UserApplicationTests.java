package com.user.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.user.dto.request.Login;
import com.user.user.dto.request.Signup;
import com.user.user.dto.response.JwtResponse;
import com.user.user.repository.UserRepo;
import com.user.user.security.jwt.JwtUtils;

@SpringBootTest
@AutoConfigureMockMvc
class UserApplicationTests {
/*
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	JwtUtils jwtUtils;

	@Test
	public void testSignUp() throws Exception {
		Set<String> role = new HashSet<String>();
		role.add("admin");
		role.add("mod");
		role.add("user");

		Signup user = new Signup("user110", "user110@g", "password", role);
		mockMvc.perform(
				post("/api/auth/signup").contentType("application/json").content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk());
		
		assertEquals("user110" , userRepo.findByUsername(user.getUsername()).getUsername() );
	}

	@Test
	public void testSignIn() throws Exception {
		Login user = new Login("user1", "password");
		mockMvc.perform(post("/api/auth/signin").contentType("application/json")
				.content(objectMapper.writeValueAsString(user))).andExpect(status().isOk());

	}

	@Test
	public void testGetAll() throws Exception {
		mockMvc.perform(get("/api/test/all")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetAdmin() throws Exception {
		mockMvc.perform(get("/api/test/admin")).andExpect(status().isUnauthorized());
	}

	@Test
//	@Disabled
	@WithMockUser(username = "user1", password = "password")
	public void testGetMod() throws Exception {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("user1", "password"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtUtils.generateJwtToken(authentication);

	//	String token = JwtUtils.generateJwtToken(new UsernamePasswordAuthenticationToken("user1", "password"));

		mockMvc.perform(get("/api/test/mod").header(HttpHeaders.AUTHORIZATION, "Bearer " + token)).andExpect(status().isOk());
	}
*/
}
