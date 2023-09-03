package com.user.user.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.user.user.conditional.NameEmail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NameEmail(fieldName = "username", dependFieldName = "email", message = "The email must contain your username")
public class Signup{

	  @NotBlank
	  @Size(min = 3, max = 20)
	  private String username;

	  @NotBlank
	  @Size(max = 50)
	  @Email
	  private String email;

	  @NotBlank
	  @Size(min = 6, max = 40)
	  private String password;
	
	  private Set<String> role;
	  
}




