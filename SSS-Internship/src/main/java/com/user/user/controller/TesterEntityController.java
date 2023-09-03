package com.user.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.user.dto.response.MsgResponse;
import com.user.user.entity.TesterEntity;
import com.user.user.repository.TesterEntityRepo;

@RestController
@RequestMapping("/testerEntity")
public class TesterEntityController {

	@Autowired
	TesterEntityRepo testerEntityRepo;
	
	@PostMapping("/createTest")
	public ResponseEntity<?> createTests(@RequestBody TesterEntity username){
		testerEntityRepo.save(username);
		return ResponseEntity.ok(new MsgResponse("User registered successfully!"));
	}
	
	@DeleteMapping("/deleteTest/{id}")
	public ResponseEntity<?> deleteTests(@PathVariable(value = "id") Long id){
		testerEntityRepo.deleteById(id);
		return ResponseEntity.ok(new MsgResponse("User deleted successfully!"));
	}
	
	
}
