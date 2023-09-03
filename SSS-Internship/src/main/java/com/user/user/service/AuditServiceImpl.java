package com.user.user.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.user.entity.AuditData;
import com.user.user.repository.AuditRepo;

@Service
public class AuditServiceImpl implements IAuditService{

	@Autowired
	AuditRepo auditRepo;

	@Override
	public List<AuditData> getAll() {
		return auditRepo.findAll();
	}
	
	@Override
	public List<AuditData> getAllOfAction(String action) {
		String act = action.toUpperCase();
		return auditRepo.findAllByAction(act);
	}

	@Override
	public List<AuditData> getAllOfEditedBy(String username) {
		return auditRepo.findAllByEditedBy(username);
	}
	
//	@Override
//	public List<AuditData> getAllOfUser(String username) {
//		return auditRepo.findAllByUsername(username);
//	}

	
	@Override
	public List<AuditData> getAllOnDate(LocalDate date) {
		return auditRepo.findAllByTimestampBetween(date.atStartOfDay(),date.atTime(23, 59, 59, 999999999));
	}
	
	@Override
	public List<AuditData> getAllWithinDate(LocalDate from, LocalDate to) {	
		return auditRepo.findAllByTimestampBetween(from.atStartOfDay(),to.atTime(23, 59, 59, 999999999));
	}

}
