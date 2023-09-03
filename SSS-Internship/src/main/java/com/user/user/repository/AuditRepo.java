package com.user.user.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.user.user.entity.AuditData;

@Repository
public interface AuditRepo extends MongoRepository<AuditData, String> {
	
	List<AuditData> findAllByAction(String action);
	List<AuditData> findAllByEditedBy(String edittedBy);
	//List<AuditData> findAllByUsername(String username);
	
	List<AuditData> findAllByTimestampBetween(LocalDateTime startTime, LocalDateTime endTime);	

} 