package com.lms.api.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.api.demo.entity.Tbl_lms_skill_master;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.jpa.repository.UserSkillMasterRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class LmsSkillApiApplicationTests {
	@Autowired
	private UserSkillMasterRepository repository;

	@Test
	@Order(1)  
	void createSkill() {
		Tbl_lms_skill_master skill = new Tbl_lms_skill_master(16 , "RubyRails", LocalDateTime.now(), LocalDateTime.now());
		repository.save(skill);
		assertThat(skill).isNotNull();
		assertThat(skill.getSkill_name()).isEqualTo("RubyRails");
		//Optional<Tbl_lms_skill_master> getUserById = repository.findById(skill.getSkill_id());
		//assertThat(getUserById.get().getSkill_name()).isEqualTo(skill.getSkill_name());
	}

	@Test
	@Order(2)  
	void updateSkill() {
		Tbl_lms_skill_master skillObj = repository.findById(14).get(); 
		skillObj.setSkill_name("JavaJ2EE");
		skillObj.setLast_mod_time(LocalDateTime.now());
		repository.save(skillObj);
		Optional<Tbl_lms_skill_master> newSkill = repository.findById(14);
		assertThat(newSkill.get().getSkill_name()).isEqualTo(skillObj.getSkill_name());
	}

	@Test
	@Order(3)  
	void deleteSkill() {
		Tbl_lms_skill_master skillObj = repository.findById(20).get(); 
		repository.deleteById(skillObj.getSkill_id());
		Optional<Tbl_lms_skill_master> newSkill = repository.findById(16);
		assertThat(newSkill).isEmpty();
	}

	@Test
	void findSkillById() {
		Tbl_lms_skill_master skillObj = repository.findById(1).get();
		assertThat(skillObj).isNotNull();
		assertThat(skillObj.getSkill_id()).isEqualTo(1);
	}


	@Test
	void findAllSkills() {
		List<Tbl_lms_skill_master> skillList = repository.findAll();
		if(skillList!=null) {
			ListIterator<Tbl_lms_skill_master> skillListIterator = skillList.listIterator();
			while (skillListIterator.hasNext()) {
				System.out.println(skillListIterator.next().getSkill_id());
			}
		}
		assertThat(skillList).asList().isNotNull();
	}

}
