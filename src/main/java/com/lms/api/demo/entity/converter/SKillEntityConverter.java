package com.lms.api.demo.entity.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.lms.api.demo.DTO.Tbl_lms_skill_masterDTO;
import com.lms.api.demo.entity.Tbl_lms_skill_master;

@Component
public class SKillEntityConverter {

	public Tbl_lms_skill_master getOneSkillConverter(Optional<Tbl_lms_skill_master> skillObj) {
		Tbl_lms_skill_master skillDTO = new Tbl_lms_skill_master();
		skillDTO.setSkill_id(skillObj.get().getSkill_id());
		skillDTO.setSkill_name(skillObj.get().getSkill_name());
		return skillDTO;
		
	}
	public Tbl_lms_skill_master createSKillConverter(Tbl_lms_skill_master skillObj) {
		Tbl_lms_skill_master newSkilObj = new Tbl_lms_skill_master();
		newSkilObj.setCreation_time(LocalDateTime.now());
		newSkilObj.setLast_mod_time(LocalDateTime.now());
		newSkilObj.setSkill_id(skillObj.getSkill_id());
		newSkilObj.setSkill_name(skillObj.getSkill_name());
		return newSkilObj;
	}
	
	public Tbl_lms_skill_master updateSKillConverter(Tbl_lms_skill_master newSkill, LocalDateTime creationTime,int id) {
		Tbl_lms_skill_master newSkillObj = new Tbl_lms_skill_master();
		newSkillObj.setCreation_time(creationTime);
		newSkillObj.setLast_mod_time(LocalDateTime.now());
		newSkillObj.setSkill_id(newSkill.getSkill_id());
		newSkillObj.setSkill_name(newSkill.getSkill_name());
		newSkillObj.setSkill_id(id);
		return newSkillObj;
	}
	
	public Tbl_lms_skill_master getAllSkillConverter(Tbl_lms_skill_master skillObj) {
		Tbl_lms_skill_master skillDTO = new Tbl_lms_skill_master();
//		Optional<Tbl_lms_skill_master> userSkill = new 
//		userList.stream().forEach(user -> {
//			UserDto userDto = mapEntityToDto_User(user);
//			userDtoList.add(userDto);
//		});
		skillDTO.setSkill_id(skillObj.getSkill_id());
		skillDTO.setSkill_name(skillObj.getSkill_name());
		return skillDTO;
		
	}
	
	public Tbl_lms_skill_masterDTO customMsgObject(Tbl_lms_skill_master lmsSkill, String methodName) {
		JSONObject responseMsg = new JSONObject();
		Tbl_lms_skill_masterDTO responseObject = new Tbl_lms_skill_masterDTO();
		if(methodName.equalsIgnoreCase("POST")) {
		responseObject = enityToDto(lmsSkill);
		responseMsg.put(lmsSkill.getSkill_id(), "Skill successfully Created!");
		responseObject.setMessage(responseMsg);
		}
		else if(methodName.equalsIgnoreCase("UPDATE")) {
			responseObject = enityToDto(lmsSkill);
			responseMsg.put(lmsSkill.getSkill_id(), "User Successfully Updated!");
			responseObject.setMessage(responseMsg);
		}else if(methodName.equalsIgnoreCase("DELETE")) {
			responseObject = enityToDto(lmsSkill);
			responseMsg.put(lmsSkill.getSkill_id(), "User Successfully Deleted!");
			responseObject.setMessage(responseMsg);
		}
		return responseObject;
	}

	private Tbl_lms_skill_masterDTO enityToDto(Tbl_lms_skill_master lmsSkill) {
		Tbl_lms_skill_masterDTO dtoSkill = new Tbl_lms_skill_masterDTO();
		dtoSkill.setCreation_time(LocalDateTime.now());
		dtoSkill.setLast_mod_time(LocalDateTime.now());
		dtoSkill.setSkill_id(lmsSkill.getSkill_id());
		dtoSkill.setSkill_name(lmsSkill.getSkill_name());
		return dtoSkill;
	}

	
	public List<Tbl_lms_skill_master> getAllSkills(List<Tbl_lms_skill_master> userList){
		return userList.stream().map(x->getAllSkillConverter(x)).collect(Collectors.toList());
	}
}
