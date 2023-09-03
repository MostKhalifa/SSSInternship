package com.user.user.controller;

import java.time.LocalDateTime;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.user.user.entity.AuditData;
import com.user.user.entity.TesterEntity;
import com.user.user.entity.User;
import com.user.user.repository.AuditRepo;
import com.user.user.repository.UserRepo;
import com.user.user.security.jwt.JwtUtils;

@Component
public class AuditListener {

	@Autowired
	AuditRepo auditRepo;
		
	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	private HttpServletRequest request;

	
		protected String getJWT(HttpServletRequest request) {
			final String requestTokenHeader =  request.getHeader("Authorization");
			return requestTokenHeader.substring(7);
		}
		
	    @PostPersist
	    public void onPrePersist(Object table) {
	        saveAuditNoToken(table, "CREATE");
	    }

	    @PostUpdate
	    public void onPreUpdate(Object table ) {
	        saveAudit(table, "UPDATE");
	    }

	    @PostRemove
	    public void onPreRemove(Object table) {
	        saveAudit(table, "DELETE");
	    }

	    
	    private void saveAuditNoToken(Object table, String action ) {
	        AuditData audit = new AuditData();
	        if(table instanceof User user){
		        audit.setTable(user);
		        audit.setAffectedThingId(user.getId());	        
	        }
	        if(table instanceof TesterEntity testerEntity) {
	        	audit.setTable(testerEntity);
		        audit.setAffectedThingId(testerEntity.getId());
	        }

	        audit.setAction(action);
	        
	        //audit.setAuditInformation(user);
	        audit.setTimestamp(LocalDateTime.now());
	        
        	auditRepo.save(audit);
	    }
	    
	    private void saveAudit(Object table, String action ) {
	        AuditData audit = new AuditData();
	        
	        if(table instanceof User user){
		        audit.setTable(user);
		        audit.setAffectedThingId(user.getId());  

	        }
	        if(table instanceof TesterEntity testerEntity) {
	        	audit.setTable(testerEntity);
		        audit.setAffectedThingId(testerEntity.getId());
	        }

//			final String requestTokenHeader = request.getHeader("Authorization");			
//	        String jwtToken = requestTokenHeader.substring(7);
	        audit.setEditedBy(jwtUtils.getUserNameFromJwtToken(getJWT(request)));
        	
	        audit.setAction(action);

	        audit.setTimestamp(LocalDateTime.now());

	        auditRepo.save(audit);
	    }

} 