package com.user.user.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.user.entity.AuditData;
import com.user.user.service.IAuditService;

@RestController
@RequestMapping("/audit")
public class MongoAuditController {

	@Autowired
	IAuditService auditService;
	
	@GetMapping("/getAll")
	public List<AuditData> getAll(){
		return auditService.getAll();
	}
	
	@GetMapping("/getByAction")
	public List<AuditData> getByAction(@RequestParam String action){
		return auditService.getAllOfAction(action);
	}
	
	@GetMapping("/getByEditor")
	public List<AuditData> getByEditor(@RequestParam String editor){
		return auditService.getAllOfEditedBy(editor);
	}
	
//	@GetMapping("/getByUsername")
//	public List<AuditData> getByUsername(@RequestParam String username){
//		return auditService.getAllOfUser(username);
//	}
	
	@GetMapping("/getOnDate")
	public List<AuditData> getOnDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date){
		return auditService.getAllOnDate(date);
	}
	
	
	@GetMapping("/getRangeDate")
	public List<AuditData> getRangeDate(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate from, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate to){
		return auditService.getAllWithinDate(from, to);
	}
	
}
