package com.lms.api.demo.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.converter.LMSUserEntityConverter;
import com.lms.api.demo.service.UserDAOService;

/**
 * @author Administrator
 *
 */
@RestController
//@JsonInclude(Include.NON_ABSENT)
public class LMSUserController {

	
	@Autowired
	private UserDAOService userDaoservice;
	
	@Autowired
	LMSUserEntityConverter convert = new LMSUserEntityConverter();

	@GetMapping("/Users")
	public ResponseEntity<List<Tbl_lms_user>> retrieveAllUsers() {
		List<Tbl_lms_user> returnList = userDaoservice.findAll();
		if(returnList.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(returnList));
	}

	
	@GetMapping("/Users/{id}")
	public ResponseEntity<Tbl_lms_user> findoneuser(@PathVariable String id) throws JsonProcessingException  {
		Tbl_lms_user lms_user =  userDaoservice.finOneUser(id);
		if(lms_user == null) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.of(Optional.of(lms_user));
	}
	
	
	
	
	 
	@PostMapping("/Users")
	ResponseEntity<UserInfoDTO> createUser(@RequestBody Tbl_lms_user newUser) {
		if(newUser == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		UserInfoDTO lms_user = userDaoservice.createUser(newUser);
		return ResponseEntity.of(Optional.of(lms_user));
	}

	
	@PutMapping("/Users/{id}")
	ResponseEntity<UserInfoDTO> updateUser(@RequestBody Tbl_lms_user newUser, @PathVariable String id) {
		
		UserInfoDTO lms_user = userDaoservice.updateUser(newUser);
		if(lms_user == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.of(Optional.of(lms_user));
	}
	
	@DeleteMapping("/User/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		if(id!= null) {
		userDaoservice.deleteUser(id);
		return ResponseEntity
	            .status(HttpStatus.CREATED)                 
	            .body(id+" " +"Deleted");
	} else {
		return new ResponseEntity<String>(id, HttpStatus.NOT_FOUND);
	}
		
		
}
}