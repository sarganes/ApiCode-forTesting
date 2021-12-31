package com.lms.api.demo.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.api.demo.DTO.Tbl_lms_userskill_mapDTO;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.DTO.UserSkillSetMapping;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;
import com.lms.api.demo.entity.converter.LMSSkillMapEnitityConverter;
import com.lms.api.demo.jpa.repository.UserSkillMaprepository;

@Service
public class UserSkillMapDAOService {

	@Autowired
	private UserSkillMaprepository repository;
	@Autowired 
	private LMSSkillMapEnitityConverter converter;
	
	@Autowired
	private EntityManager entityManager;

	public List<Tbl_lms_userskill_map> findAll() {

		List<Tbl_lms_userskill_map> skillMapList = repository.findAll();	
				return skillMapList;
	}
	
	public Tbl_lms_userskill_map findOneSkillMap(String id) {
		return repository.getById(id);
				
	}
	
	public Tbl_lms_userskill_mapDTO createSkillMap(Tbl_lms_userskill_map skillMap) {
		String methodName = "POST";
		Tbl_lms_userskill_map createSkillMap = converter.enityToDtoForPost(skillMap);
		Tbl_lms_userskill_map savedUserSkillMap = repository.saveAndFlush(createSkillMap);
		
		return  converter.customMsgObject(savedUserSkillMap,methodName);			

		
	}
	
	public Tbl_lms_userskill_mapDTO UpdateSkillMap(Tbl_lms_userskill_map skillMap,String id) {
		String methodName = "UPDATE";
		System.out.println("id"+id);
		Tbl_lms_userskill_map findCorrectSkillMap = findOneSkillMap(id);
		Tbl_lms_userskill_map skillMapupdate = converter.enityToDtoForUpdate(findCorrectSkillMap);
		Tbl_lms_userskill_map savedUserSkillMap = repository.saveAndFlush(skillMapupdate);
		return converter.customMsgObject(savedUserSkillMap,methodName);			

		
	} 
	
	public void   deleteUser(String id) {
		entityManager.createQuery("delete from tbl_lms_userskill_map e where e.user_skill_id = :id");
		 
	}
	
	public List<UserSkillSetMapping> getUserSkillMaps() throws JsonProcessingException {
		List<Tbl_lms_userskill_map> lmsUsrList = repository.findAll();
//		List<String> userIdList = repository.getAllUsers();
////		System.out.println("userList"+userIdList.get(0));
//		List<String> userList = repository.getUserData(userIdList);
////		System.out.println(userList.get(1));
////		return null;
		return converter.getUserDetails(lmsUsrList);
	}
	
	public UserSkillSetMapping getOneUserSkillMaps(String id) throws JsonProcessingException {
		System.out.println(id);
		Tbl_lms_userskill_map lmsUsrskillMap = repository.getById(id);
		return converter.getOneUserDetails(lmsUsrskillMap);
	}

	public List<UserSkillSetMapping>  findSkillsBySkillId(int id) {
		List<Tbl_lms_userskill_map> userskillMapList = repository.getAllUsers(id);
		return converter.getOneUserDetailsBySkillID(userskillMapList);
		 
	}

}
