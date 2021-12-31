package com.lms.api.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lms.api.demo.config.UserIdGenerator;

@Entity(name = "tbl_lms_userskill_map")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tbl_lms_userskill_map implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Tbl_lms_userskill_map(String user_skill_id, int skill_id, int months_of_exp, LocalDateTime creation_time,
			LocalDateTime last_mod_time) {
		super();
		this.user_skill_id = user_skill_id;
		this.skill_id = skill_id;
		this.months_of_exp = months_of_exp;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}

	public Tbl_lms_userskill_map() {
		// TODO Auto-generated constructor stub
	}

	

	public Tbl_lms_userskill_map(String user_skill_id, int skill_id, int months_of_exp, LocalDateTime creation_time,
			LocalDateTime last_mod_time, String user_id) {
		super();
		this.user_skill_id = user_skill_id;
		this.skill_id = skill_id;
		this.months_of_exp = months_of_exp;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
		this.user_id = user_id;
	}



	public Tbl_lms_userskill_map(String user_skill_id, String user_id, int skill_id, int months_of_exp, LocalDateTime creation_time, LocalDateTime last_mod_time) {
		super();
		this.user_skill_id = user_skill_id;
		this.user_id = user_id;
		this.skill_id = skill_id;
		this.months_of_exp = months_of_exp;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_skill_id")
	@GenericGenerator(name = "user_skill_id", strategy = "com.lms.api.demo.config.UserSkillIdGenrator", parameters = {
			@Parameter(name = UserIdGenerator.INCREMENT_PARAM, value = "01"),
			@Parameter(name = UserIdGenerator.VALUE_PREFIX_DEFAULT, value = "US"),
			@Parameter(name = UserIdGenerator.NUMBER_FORMAT_DEFAULT, value = "%1d") })
	private String user_skill_id;
	private int skill_id;
	private int months_of_exp;
	private LocalDateTime creation_time;
	private LocalDateTime last_mod_time;
	@Column(name = "user_id", insertable = true, updatable = false)
	private String user_id;


	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_skill_id() {
		return user_skill_id;
	}

	public void setUser_skill_id(String user_skill_id) {
		this.user_skill_id = user_skill_id;
	}

	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@JsonIgnore
	private Tbl_lms_user lms_usr;
	
	public Tbl_lms_user getLms_usr() {
		return lms_usr;
	}

	public void setLms_usr(Tbl_lms_user lms_usr) {
		this.lms_usr = lms_usr;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="skill_id" , referencedColumnName = "skill_id", insertable = false, updatable = false)
	@JsonIgnore
	private Tbl_lms_skill_master lms_skill_master;

	public Tbl_lms_skill_master getLms_skill_master() {
		return lms_skill_master;
	}

	public void setLms_skill_master(Tbl_lms_skill_master lms_skill_master) {
		this.lms_skill_master = lms_skill_master;
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

	

}
