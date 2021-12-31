package com.lms.api.demo.entity.converter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.lms.api.demo.DTO.Tbl_lms_userskill_mapDTO;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.DTO.UserSkillSetMapping;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.Tbl_lms_userskill_map;

@Component

public class LMSSkillMapEnitityConverter {

	public Tbl_lms_userskill_mapDTO customMsgObject(Tbl_lms_userskill_map lmsUserSkillMap, String methodName) {
		JSONObject responseMsg = new JSONObject();
		Tbl_lms_userskill_mapDTO responseObject = enityToDto(lmsUserSkillMap);
		enityToDto(lmsUserSkillMap);
		if (methodName.equalsIgnoreCase("POST")) {
			responseMsg.put(lmsUserSkillMap.getUser_skill_id(), "User successfully Created!");
			responseObject.setMessage(responseMsg);
		} else if (methodName.equalsIgnoreCase("UPDATE")) {
			responseMsg.put(lmsUserSkillMap.getUser_skill_id(), "User Successfully Updated ");
			responseObject.setMessage(responseMsg);

		} else if (methodName.equalsIgnoreCase("DELETE")) {
			responseMsg.put("", "User Successfully Deleted ");
			responseObject.setMessage(responseMsg);
		}
		return responseObject;
	}

	public Tbl_lms_userskill_map enityToDtoForPost(Tbl_lms_userskill_map skillMap) {
		Tbl_lms_userskill_map skillMapNew = new Tbl_lms_userskill_map();
		skillMapNew.setUser_id(skillMap.getUser_id());
		System.out.println("skillId"+skillMap.getSkill_id());
		skillMapNew.setSkill_id(skillMap.getSkill_id());
		skillMapNew.setMonths_of_exp(skillMap.getMonths_of_exp());
		if (skillMap.getCreation_time() == null) {
			skillMapNew.setCreation_time(LocalDateTime.now());
		}
		if (skillMap.getLast_mod_time() == null) {
			skillMapNew.setLast_mod_time(LocalDateTime.now());
		}

		return skillMapNew;
	}
	
	public Tbl_lms_userskill_map enityToDtoForUpdate(Tbl_lms_userskill_map skillMap) {
		Tbl_lms_userskill_map skillMapNew = new Tbl_lms_userskill_map();
		skillMapNew.setUser_skill_id(skillMap.getUser_skill_id());
		skillMapNew.setUser_id(skillMap.getUser_id());
		skillMapNew.setSkill_id(skillMap.getSkill_id());
		skillMapNew.setMonths_of_exp(skillMap.getMonths_of_exp());
		skillMapNew.setCreation_time(skillMap.getCreation_time());
		skillMapNew.setLast_mod_time(LocalDateTime.now());
		return skillMapNew;
	}

	public Tbl_lms_userskill_mapDTO enityToDto(Tbl_lms_userskill_map skillMap) {

		Tbl_lms_userskill_mapDTO skillMapDTO = new Tbl_lms_userskill_mapDTO();
		skillMapDTO.setUser_skill_id(skillMap.getUser_skill_id());
		skillMapDTO.setUser_id(skillMap.getUser_id());
		skillMapDTO.setSkill_id(skillMap.getSkill_id());
		skillMapDTO.setMonths_of_exp(skillMap.getMonths_of_exp());
		return skillMapDTO;

	}

	public List<UserSkillSetMapping> getUserDetails(List<Tbl_lms_userskill_map> lmsUsrList) {
		List<UserSkillSetMapping> returnList = new ArrayList<UserSkillSetMapping>();

		JSONObject skillMapObj = new JSONObject();
		Map<String, String> skillMap = new HashMap();
		JSONArray array = new JSONArray();
		for (int i = 0; i < lmsUsrList.size(); i++) {
			UserSkillSetMapping lms_user = new UserSkillSetMapping();
			lms_user.setUserId(lmsUsrList.get(i).getUser_id());
			lms_user.setFirstName(lmsUsrList.get(i).getLms_usr().getName());
			lms_user.setLastName(lmsUsrList.get(i).getLms_usr().getLastname());
			skillMap.put("id:", String.valueOf(lmsUsrList.get(i).getSkill_id()));
			skillMap.put("skill:", lmsUsrList.get(i).getLms_skill_master().getSkill_name());
			skillMapObj.putAll(skillMap);
			lms_user.setSkillmap(skillMapObj);
			returnList.add(lms_user);

		}

		return returnList;
	}

	public UserSkillSetMapping getOneUserDetails(Tbl_lms_userskill_map skillMapInputObj) {

		JSONObject skillMapObj = new JSONObject();
		Map<String, String> skillMap = new HashMap();
		UserSkillSetMapping lms_user = new UserSkillSetMapping();
		lms_user.setUserId(skillMapInputObj.getUser_id());
		lms_user.setFirstName(skillMapInputObj.getLms_usr().getName());
		lms_user.setLastName(skillMapInputObj.getLms_usr().getLastname());
		JSONArray array = new JSONArray();
		List<Tbl_lms_userskill_map> skillIdList = skillMapInputObj.getLms_usr().getUsrSkillIdMap();
		if (skillIdList.size() != 0) {
			for (int i = 0; i < skillIdList.size(); i++) {
				array.add("Skill ID:" + skillIdList.get(i).getSkill_id());
				array.add("Skill Name:" + skillIdList.get(i).getLms_skill_master().getSkill_name());
			}
			skillMapObj.put("SKills", array);
			lms_user.setSkillmap(skillMapObj);

			
		}
		return lms_user;
	}

	public List<UserSkillSetMapping> getOneUserDetailsBySkillID(List<Tbl_lms_userskill_map> userskillMapList) {
	return getUserDetails(userskillMapList);
		
	}
	
}
