package com.lms.api.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lms.api.demo.DTO.Tbl_lms_skill_masterDTO;
import com.lms.api.demo.entity.Tbl_lms_skill_master;
import com.lms.api.demo.entity.converter.SKillEntityConverter;
import com.lms.api.demo.jpa.repository.UserSkillMasterRepository;

@Service
public class SkillDAOService {

	
	@Autowired
	UserSkillMasterRepository userMasterepository;
	@Autowired SKillEntityConverter convert;

	public List<Tbl_lms_skill_master> findAll() {
		return userMasterepository.findAll();
	}

	public Tbl_lms_skill_masterDTO createSkill(Tbl_lms_skill_master newSkill) {
		Tbl_lms_skill_master modifiedSkillObj = convert.createSKillConverter(newSkill);
		String methodName = "POST";
		System.out.println("the skill_id is"+newSkill.getSkill_id());
		Tbl_lms_skill_master savedNewSkill = userMasterepository.saveAndFlush(modifiedSkillObj);

		return convert.customMsgObject(savedNewSkill,methodName);
	}

	public Tbl_lms_skill_master findOneSkill(int id) throws JsonProcessingException {
		return userMasterepository.findById(id).get();
	}

	
	public Tbl_lms_skill_master findoneSkill(int id) {
		Tbl_lms_skill_master skillDTO = new Tbl_lms_skill_master();
		Optional<Tbl_lms_skill_master> skill = userMasterepository.findById(id) ;
		skillDTO = convert.getOneSkillConverter(skill);
		return skillDTO;
	}
	
	public Optional<Tbl_lms_skill_master> findOneSkillName(int id) throws JsonProcessingException {
		return userMasterepository.findById(id);
	}

	public Tbl_lms_skill_masterDTO updateSkill(Tbl_lms_skill_master newSkill,int id) {
		String methodName = "UPDATE";
		Tbl_lms_skill_master lmsSkill =userMasterepository.getById(id); 
		Tbl_lms_skill_master updateSkill = convert.updateSKillConverter(newSkill,lmsSkill.getCreation_time(),id);
		Tbl_lms_skill_master updatedSkill = userMasterepository.save(updateSkill);
		return convert.customMsgObject(updatedSkill,methodName);
	}

	public void deleteSkill(int id) {
		userMasterepository.deleteById(id);
	}
	 
	
	
}
