package com.lms.api.demo;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.api.demo.entity.Tbl_lms_userskill_map;
import com.lms.api.demo.jpa.repository.UserSkillMaprepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class LmsSkillMapApiDemoApplicationTests {
	@Autowired
	private UserSkillMaprepository repository;
	
	@Test
	@Order(1)
	public void createUserSkill() {
		Tbl_lms_userskill_map userSkill = new Tbl_lms_userskill_map("","U032", 11, 12, LocalDateTime.now(), LocalDateTime.now());
		repository.saveAndFlush(userSkill);
		assertThat(userSkill).isNotNull();
		assertThat(userSkill.getUser_id()).isEqualTo("U032");
	}
	
	@Test
	@Order(2)
	void updateUserSkill() {
		Tbl_lms_userskill_map skillObj = repository.findById("US01").get();
		skillObj.setMonths_of_exp(30);
		skillObj.setLast_mod_time(LocalDateTime.now());
		repository.save(skillObj);
		Optional<Tbl_lms_userskill_map> newSkill = repository.findById("US01");
		assertThat(newSkill.get().getMonths_of_exp()).isEqualTo(30);
	}
	
	@Test
	@Order(3)
	void deleteUserSkill() {
		Tbl_lms_userskill_map skillObj = repository.findById("US13").get();
		boolean isExistBeforeDelete = repository.findById("US13").isPresent();
		repository.deleteById(skillObj.getUser_skill_id());
		boolean isExistAfterDelete = repository.findById("US13").isPresent();
		assertThat(isExistBeforeDelete).isTrue();
		assertThat(isExistAfterDelete).isFalse();
	}
	
	
	@Test
	void findUserSkillsByID() {
		Tbl_lms_userskill_map skillObj = repository.findById("US01").get();
		assertThat(skillObj).isNotNull();
		assertThat(skillObj.getUser_id()).isEqualTo("U01");
	}
	
	@Test
	void findUserSkillsByInvalidID() {
		Tbl_lms_userskill_map skillObj = repository.findById("US50").get();
		assertThat(skillObj).isNull();
	}
	
	//@Test(expected = IllegalArgumentException.class)
	@Test
	void findUserSkillsByEmptyID() {
		String skill_id="";
		Tbl_lms_userskill_map skillObj = repository.findById(skill_id).get();
		assertThat(skillObj).isNull();
	} 
	@Test
	void findAllUserSkills() {
		List<Tbl_lms_userskill_map> userSkillList = repository.findAll();
		if(userSkillList!=null) {
			ListIterator<Tbl_lms_userskill_map> userSkillListIterator = userSkillList.listIterator();
			while (userSkillListIterator.hasNext()) {
				System.out.println(userSkillListIterator.next().getUser_skill_id());
			}
		}
		assertThat(userSkillList).asList().isNotNull();
	}
}
