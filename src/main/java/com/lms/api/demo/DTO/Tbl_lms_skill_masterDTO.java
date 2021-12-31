package com.lms.api.demo.DTO;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Tbl_lms_skill_masterDTO {
	private int skill_id;
	private String skill_name;
	private LocalDateTime creation_time = LocalDateTime.now();
	private LocalDateTime last_mod_time = LocalDateTime.now();
	private JSONObject message;

	public JSONObject getMessage() {
		return message;
	}

	public void setMessage(JSONObject message) {
		this.message = message;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}

	public int getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}

	public LocalDateTime getCreation_time() {
		return creation_time;
	}

	public LocalDateTime getLast_mod_time() {
		return last_mod_time;
	}

	@JsonIgnore
	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}

	@JsonIgnore
	public void setLast_mod_time(LocalDateTime last_mod_time) {
		this.last_mod_time = last_mod_time;
	}

}
