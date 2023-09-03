package com.user.user.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.RedisHash;

import com.user.user.conditional.NameEmail;
import com.user.user.conditional.NameEmailCondition;
import com.user.user.controller.AuditListener;

import lombok.*;


@Entity
@Table(name = "users")

@SQLDelete(sql = "UPDATE users	 SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class User{
	@Id
	@Setter(AccessLevel.PROTECTED)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username" ,unique = true)
	@NotBlank(message = "Username is mandatory")
	private String username;


	@Column(name = "email", updatable = false ,unique = true)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Please enter a valid email address")
	private String email;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, message = "Please enter a password over 8 characters")
	private String password;
	
	private boolean deleted = Boolean.FALSE;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
