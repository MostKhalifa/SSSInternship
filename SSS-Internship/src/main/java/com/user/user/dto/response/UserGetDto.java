package com.user.user.dto.response;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
//@AllArgsConstructor
public class UserGetDto {

	private Long id;
	@NotBlank(message = "Username is mandatory")
	private String username;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Please enter a valid email address")
	private String email;

//	private Collection<Role> role = new ArrayList<>();

}