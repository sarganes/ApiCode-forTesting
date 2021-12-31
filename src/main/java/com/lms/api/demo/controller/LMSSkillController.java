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
import com.lms.api.demo.DTO.Tbl_lms_skill_masterDTO;
import com.lms.api.demo.entity.Tbl_lms_skill_master;
import com.lms.api.demo.entity.converter.SKillEntityConverter;
import com.lms.api.demo.service.SkillDAOService;

@RestController
public class LMSSkillController {

	@Autowired
	private SkillDAOService skillDaoservice;
	@Autowired
	SKillEntityConverter convert;

	@GetMapping("/AllSkills")
	public ResponseEntity<List<Tbl_lms_skill_master>> retrieveAllSKill() {
		List<Tbl_lms_skill_master> skillList = skillDaoservice.findAll();
		List<Tbl_lms_skill_master> returnList = convert.getAllSkills(skillList);
		if (skillList.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(returnList));
	}

	@GetMapping("/Skills/{id}")
	ResponseEntity<Tbl_lms_skill_master> findoneskill(@PathVariable int id) throws JsonProcessingException {

		Tbl_lms_skill_master skill = skillDaoservice.findoneSkill(id);
		if (skill == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			return ResponseEntity.of(Optional.of(skill));
		}
	}

	@PostMapping("/Skills")
	ResponseEntity<Object> createSkill(@RequestBody Tbl_lms_skill_master newSkill) {
		if (newSkill == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(skillDaoservice.createSkill(newSkill)));

	}

	@PutMapping("/Skills/{id}")
	ResponseEntity<Tbl_lms_skill_masterDTO> updateSkill(@RequestBody Tbl_lms_skill_master newSkill, @PathVariable int id) {
		if (newSkill == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(skillDaoservice.updateSkill(newSkill,id)));

	}

	@DeleteMapping("/skills/{id}")
	public ResponseEntity<String> deleteSkill(@PathVariable int id) {
		if (id != 0) {
			skillDaoservice.deleteSkill(id);
			return ResponseEntity.status(HttpStatus.CREATED).body(id + " " + "Deleted");
		} else {
			return new ResponseEntity<String>(String.valueOf(id), HttpStatus.NOT_FOUND);
		}
	}
}
