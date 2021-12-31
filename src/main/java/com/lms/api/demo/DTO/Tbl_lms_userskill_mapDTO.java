package com.lms.api.demo.DTO;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Component
public class Tbl_lms_userskill_mapDTO {
	private String user_skill_id;
	private int skill_id;
	private int months_of_exp;
	private String user_id;
	private LocalDateTime creation_time;
	private LocalDateTime last_mod_time;
	private JSONObject message;
	
	public String getUser_skill_id() {
		return user_skill_id;
	}
	public void setUser_skill_id(String user_skill_id) {
		this.user_skill_id = user_skill_id;
	}
	public int getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	public int getMonths_of_exp() {
		return months_of_exp;
	}
	public void setMonths_of_exp(int months_of_exp) {
		this.months_of_exp = months_of_exp;
	}
	@JsonIgnore
	public LocalDateTime getCreation_time() {
		return creation_time;
	}
	@JsonIgnore
	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}
	@JsonIgnore
	public LocalDateTime getLast_mod_time() {
		return last_mod_time;
	}
	@JsonIgnore
	public void setLast_mod_time(LocalDateTime last_mod_time) {
		this.last_mod_time = last_mod_time;
	}

	
	public JSONObject getMessage() {
		return message;
	}
	public void setMessage(JSONObject message) {
		this.message = message;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
	
	// get this object from service method 
	// set all the parameters make a list and send to the service class User DTO
	
	
}
