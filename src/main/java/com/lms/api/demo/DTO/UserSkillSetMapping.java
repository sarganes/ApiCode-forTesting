package com.lms.api.demo.DTO;


import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.lms.api.demo.entity.Tbl_lms_skill_master;
import com.lms.api.demo.entity.Tbl_lms_user;

@Component
public class UserSkillSetMapping {
	private String userId;
	private String firstName;
	private String lastName;
	private JSONObject skillmap;
	public JSONObject getSkillmap() {
		return skillmap;
	}
	public void setSkillmap(JSONObject skillmap) {
		this.skillmap = skillmap;
	}
	//private Tbl_lms_user user;
	
	
//	public Tbl_lms_user getUser() {
//		return user;
//	}
//	public void setUser(Tbl_lms_user user) {
//		this.user = user;
//	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
