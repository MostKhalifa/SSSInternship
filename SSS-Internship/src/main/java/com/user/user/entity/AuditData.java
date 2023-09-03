package com.user.user.entity;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.user.user.dto.UserAuditDataDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection= "auditDataReviewed")
public class AuditData {
	
	 @Id
	 private String id;

	 private String action;
	 private String editedBy; 
	 
	 private Long affectedThingId;
	 
	 
	 private Object table;
//	 private Long userId;
//	 private String username;
//	 private String email;
//	 private Set<Role> roles = new HashSet<>();
	 
	 //private UserAuditDataDto auditedData;
	 //private User auditInformation;

	 private LocalDateTime timestamp;



}
