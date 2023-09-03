package com.user.user.service;

import java.time.LocalDate;
import java.util.List;

import com.user.user.entity.AuditData;

public interface IAuditService {
	List<AuditData> getAll();
	List<AuditData> getAllOfAction(String action);
	List<AuditData> getAllOfEditedBy(String username);
	//List<AuditData> getAllOfUser(String username);
	
	List<AuditData> getAllOnDate(LocalDate date);
	List<AuditData> getAllWithinDate(LocalDate from , LocalDate to);
	
}
