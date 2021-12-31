package com.lms.api.demo.DTO;

import java.time.LocalDateTime;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
public class UserInfoDTO {
	
	
	private String user_id;
	private String user_first_name;
	private String user_last_name;
	private String name;
	private long phone_number;
	private String location;
	private String time_zone;
	private String linkedin_url;
	private String education_ug;
	private String education_pg;
	private String comments;
	private String  visa_status;
	private LocalDateTime creation_time;
	private LocalDateTime last_mod_time;
	private JSONObject message;
	
	
	
	//@JsonIgnore
	public JSONObject getMessage() {
		return message;
	}
	public void setMessage(JSONObject message) {
		this.message = message;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public String getLocation() {
		return location;
	}
	public String getTime_zone() {
		return time_zone;
	}
	public String getLinkedin_url() {
		return linkedin_url;
	}
	public String getEducation_ug() {
		return education_ug;
	}
	public String getEducation_pg() {
		return education_pg;
	}
	public String getComments() {
		return comments;
	}
	public String getVisa_status() {
		return visa_status;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}
	public void setLinkedin_url(String linkedin_url) {
		this.linkedin_url = linkedin_url;
	}
	public void setEducation_ug(String education_ug) {
		this.education_ug = education_ug;
	}
	public void setEducation_pg(String education_pg) {
		this.education_pg = education_pg;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setVisa_status(String visa_status) {
		this.visa_status = visa_status;
	}

	public String getName() {
		return name;
	}
	public void setName(String fullName) {
		this.name = fullName;
	}
	
	public String getUser_last_name() {
		return user_last_name;
	}
	@JsonIgnore
	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@JsonIgnore
	public String getUser_first_name() {
		return user_first_name;
	}
	@JsonIgnore
	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}
	
	@JsonIgnore
	public void setUser_edu_ug(String user_edu_ug) {
		this.education_ug = user_edu_ug;
	}
	public String getUser_edu_pg() {
		return education_pg;
	}
	@JsonIgnore
	public void setUser_edu_pg(String user_edu_pg) {
		this.education_pg = user_edu_pg;
	}
	public String getUser_comments() {
		return comments;
	}
	@JsonIgnore
	public void setUser_comments(String user_comments) {
		this.comments = user_comments;
	}
	
	public String getUser_visa_status() {
		return visa_status;
	}
	@JsonIgnore
	public void setUser_visa_status(String user_visa_status) {
		this.visa_status = user_visa_status;
	}
	@JsonIgnore
	public LocalDateTime getCreation_time() {
		return creation_time;
	}
	@JsonIgnore
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
