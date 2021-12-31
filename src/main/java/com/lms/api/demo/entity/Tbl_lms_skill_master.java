package com.lms.api.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "tbl_lms_skill_master")
public class Tbl_lms_skill_master implements Serializable {

	private static final long serialVersionUID = -3322113303362981686L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	private int skill_id;
	private String skill_name;
	private LocalDateTime creation_time;
	public Tbl_lms_skill_master(int skill_id, String skill_name, LocalDateTime creation_time,
			LocalDateTime last_mod_time) {
		super();
		this.skill_id = skill_id;
		this.skill_name = skill_name;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}

			

	public LocalDateTime getCreation_time() {
		return creation_time;
	}

	public LocalDateTime getLast_mod_time() {
		return last_mod_time;
	}

		private LocalDateTime last_mod_time;
	@JsonIgnore
	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}
	@JsonIgnore
	public void setLast_mod_time(LocalDateTime last_mod_time) {
		this.last_mod_time = last_mod_time;
	}
	public int getSkill_id() {
		return skill_id;
	}

	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}

	public String getSkill_name() {
		return skill_name;
	}

	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}


	public Tbl_lms_skill_master() {

	}

}
