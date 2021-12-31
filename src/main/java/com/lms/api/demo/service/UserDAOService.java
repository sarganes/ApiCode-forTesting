package com.lms.api.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;
import com.lms.api.demo.entity.converter.LMSUserEntityConverter;
import com.lms.api.demo.jpa.repository.UserRepository;
import com.lms.api.demo.jpa.repository.UserSkillMaprepository;

@Service
@JsonInclude(value = Include.NON_NULL)
public class UserDAOService {

	@Autowired
	UserRepository repository;
	@Autowired 
	LMSUserEntityConverter converter;
	@Autowired
	UserSkillMaprepository skillMaprepository;

	public List<Tbl_lms_user> findAll() {
		
		
		List<Tbl_lms_user> userList = repository.findAll();
		List<Tbl_lms_user> returnList = converter.getAllUsers(userList);
		return returnList;
	}
	public UserInfoDTO createUser(Tbl_lms_user newUser) {
		String methodName = "POST";
		Tbl_lms_user lmsUser = converter.enityToDtoForPost(newUser);
		Tbl_lms_user savedUser = repository.saveAndFlush(lmsUser);
		return converter.customMsgObject(savedUser,methodName);
		
	}
	public Tbl_lms_user finOneUser(String id) throws JsonProcessingException {
		Tbl_lms_user userDTO = new Tbl_lms_user();
		userDTO = converter.enityToDtoGetoneUser(repository.findById(id).get());
		return userDTO;
	}
	public UserInfoDTO updateUser(Tbl_lms_user newUser) {
		String methodName = "UPDATE";
		Tbl_lms_user lmsUser =repository.findById(newUser.getUser_id()).get(); 
		Tbl_lms_user updateUser = converter.enityToDtoForUpdate(newUser,lmsUser.getCreation_time());
		Tbl_lms_user updatedUser = repository.save(updateUser);
		return converter.customMsgObject(updatedUser,methodName); 
	}

	public UserInfoDTO deleteUser(String id) {
		String methodName = "DELETE";
		repository.deleteById(id);
		return converter.customMsgObject(null,methodName); 
	}
	 
	
}
