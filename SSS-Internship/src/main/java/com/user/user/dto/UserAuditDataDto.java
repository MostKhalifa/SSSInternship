package com.user.user.dto;

import java.util.HashSet;
import java.util.Set;


import com.user.user.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuditDataDto {

	private String username;
	private String email;
	private boolean deleted = Boolean.FALSE;
	private Set<Role> roles = new HashSet<>();
}
