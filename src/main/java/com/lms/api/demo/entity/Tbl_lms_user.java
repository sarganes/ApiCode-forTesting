package com.lms.api.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lms.api.demo.config.UserIdGenerator;


@Entity(name = "tbl_lms_user")

public class Tbl_lms_user implements Serializable  {
	public Tbl_lms_user(String user_id, String name, String lastname, long phone_number, String location,
			String time_zone, String linkedin_url, String education_ug, String education_pg, String comments,
			String visa_status, LocalDateTime creation_time, LocalDateTime last_mod_time) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.lastname = lastname;
		this.phone_number = phone_number;
		this.location = location;
		this.time_zone = time_zone;
		this.linkedin_url = linkedin_url;
		this.education_ug = education_ug;
		this.education_pg = education_pg;
		this.comments = comments;
		this.visa_status = visa_status;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}

	private static final long serialVersionUID = -3322113303362981686L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id")
	@GenericGenerator(name = "user_id", strategy = "com.lms.api.demo.config.UserIdGenerator", parameters = {
			@Parameter(name = UserIdGenerator.INCREMENT_PARAM, value = "01"),
			@Parameter(name = UserIdGenerator.VALUE_PREFIX_DEFAULT, value = "U"),
			@Parameter(name = UserIdGenerator.NUMBER_FORMAT_DEFAULT, value = "%1d") })
	private String user_id;
	@Column(name ="user_first_name")
	private String name;
	@Column(name = "user_last_name")
	private String lastname;
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "user_phone_number")
	private long phone_number;
	public long getPhone_number() {
		return phone_number;
	}

	@Column(name = "user_location")
	private String location;
	@Column(name = "user_time_zone")
	private String time_zone;
	@Column(name = "user_linkedin_url")
	private String linkedin_url;
	@Column(name = "user_edu_ug")
	private String education_ug;
	@Column(name = "user_edu_pg")
	private String education_pg;
	@Column(name = "user_comments")
	private String comments;
	@Column(name = "user_visa_status")
	private String  visa_status;
	@Column(name = "creation_time")
	private LocalDateTime creation_time;
	@Column(name = "last_mod_time")
	private LocalDateTime last_mod_time;
	

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lms_usr")
	@JsonIgnore
	private List<Tbl_lms_userskill_map> usrSkillIdMap = new ArrayList<Tbl_lms_userskill_map>();
	

	public List<Tbl_lms_userskill_map> getUsrSkillIdMap() {
		return usrSkillIdMap;
	}

	public void setUsrSkillIdMap(List<Tbl_lms_userskill_map> usrSkillIdMap) {
		this.usrSkillIdMap = usrSkillIdMap;
	}

	
	
	public Tbl_lms_user() {
		
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	@JsonIgnore
	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}
	@JsonIgnore
	public void setLast_mod_time(LocalDateTime last_mod_time) {
		this.last_mod_time = last_mod_time;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public LocalDateTime getCreation_time() {
		return creation_time;
	}

	
	public LocalDateTime getLast_mod_time() {
		return last_mod_time;
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
	@JsonIgnore
	public String getVisa_status() {
		return visa_status;
	}

	
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public void setTime_zone(String time_zone) {
		this.time_zone = time_zone;
	}

	public void setLinkedin_url(String linkedin_url) {
		this.linkedin_url = linkedin_url;
	}
	@JsonIgnore
	public void setEducation_ug(String education_ug) {
		this.education_ug = education_ug;
	}
	@JsonIgnore
	public void setEducation_pg(String education_pg) {
		this.education_pg = education_pg;
	}
	@JsonIgnore
	public void setComments(String comments) {
		this.comments = comments;
	}
	@JsonIgnore
	public void setVisa_status(String visa_status) {
		this.visa_status = visa_status;
	}

}
