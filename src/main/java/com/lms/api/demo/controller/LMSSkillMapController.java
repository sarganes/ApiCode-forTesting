package com.lms.api.demo.controller;

import java.util.List;
import java.util.Optional;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.api.demo.DTO.Tbl_lms_userskill_mapDTO;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.DTO.UserSkillSetMapping;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;
import com.lms.api.demo.entity.converter.LMSSkillMapEnitityConverter;
import com.lms.api.demo.service.UserSkillMapDAOService;

@RestController
public class LMSSkillMapController {

	@Autowired
	private UserSkillMapDAOService skillMapService;
	
	@Autowired
	LMSSkillMapEnitityConverter converter = new LMSSkillMapEnitityConverter();
	
	@GetMapping("/UserSkills")
	public ResponseEntity<List<Tbl_lms_userskill_map>> retrieveAllUserSKillMap() {
		List<Tbl_lms_userskill_map> returnList = skillMapService.findAll();
		if(returnList.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(returnList));
		
		
	}
	
	@GetMapping("/UserSkills/{id}")
	public ResponseEntity<Tbl_lms_userskill_map> findoneuser(@PathVariable String id) throws JsonProcessingException  {
		Tbl_lms_userskill_map skillMap =  skillMapService.findOneSkillMap(id);
		if(skillMap == null) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
		return ResponseEntity.of(Optional.of(skillMap));
		
	}
	
	@GetMapping("/UserSkillsMap")
	public ResponseEntity<List<UserSkillSetMapping>> getUserDetails() throws JsonProcessingException  {
		List<UserSkillSetMapping> returnList = skillMapService.getUserSkillMaps();
		if(returnList.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(returnList));
		
	}
	
	@GetMapping("/UserSkillsSet/{id}")
	public ResponseEntity<List<UserSkillSetMapping>> findSkillsBySkillId(@PathVariable int id) throws JsonProcessingException  {
		List<UserSkillSetMapping> returnList =  skillMapService.findSkillsBySkillId(id);
		if(returnList.size() == 0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}		
		return ResponseEntity.of(Optional.of(returnList));
		
	}
	
	@GetMapping("/UserSkillsMap/{id}")
	public ResponseEntity<UserSkillSetMapping> getOneUserDetails(@PathVariable String id) throws JsonProcessingException  {
		UserSkillSetMapping returnSKillMap = skillMapService.getOneUserSkillMaps(id);
		if(returnSKillMap==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(returnSKillMap));
		
	}
	
	@PostMapping("/SkillsMap")
	ResponseEntity<Tbl_lms_userskill_mapDTO> createSkillMap(@RequestBody Tbl_lms_userskill_map newUser) {
		if(newUser == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		Tbl_lms_userskill_mapDTO newSkillMap = skillMapService.createSkillMap(newUser);
		return ResponseEntity.of(Optional.of(newSkillMap));
	}
	
	@PutMapping("/SkillsMap/{id}")
	ResponseEntity<Tbl_lms_userskill_mapDTO>updateSkillMap(@RequestBody Tbl_lms_userskill_map newSkill,@PathVariable String id) {
		if(newSkill == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		Tbl_lms_userskill_mapDTO newSkillMap = skillMapService.UpdateSkillMap(newSkill,id);
		return ResponseEntity.of(Optional.of(newSkillMap));
	}
	
	
	@DeleteMapping("/UserSkills/{id}")
	public ResponseEntity<String> deleteSkillMap(@PathVariable String id) {
	if(id!= null) {
		skillMapService.deleteUser(id);
		return ResponseEntity
	            .status(HttpStatus.CREATED)                 
	            .body(id+" " +"Deleted");
	} else {
		return new ResponseEntity<String>(id, HttpStatus.NOT_FOUND);
	}
	}

}
