package com.lms.api.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.jpa.repository.UserRepository;

@SpringBootTest
class LmsApiDemoApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void createUserWithID() {
		Long phoneNumber = 12345678910L;
		Tbl_lms_user user = new Tbl_lms_user("", "Alan","Mike",phoneNumber, 
				"New Jersey", "EST", "https://www.linkedin.com/in/AlanMike/",
				"BS","MS", "", 
				"US-Citizen",  LocalDateTime.now(), LocalDateTime.now());
		userRepository.saveAndFlush(user);
		assertThat(user).isNotNull();
		assertThat(user.getName()).isEqualTo("Alan");
	}

	@Test
	void updateUser() {
		Tbl_lms_user userObj = userRepository.findById("U03").get();
		userObj.setName("Maria");
		userObj.setLast_mod_time(LocalDateTime.now());
		userRepository.save(userObj);
		Optional<Tbl_lms_user> newSkill = userRepository.findById("U03");
		assertThat(newSkill.get().getName()).isEqualTo("Maria");
	}

	@Test
	void deleteUser() {
		Tbl_lms_user userObj = userRepository.findById("U30").get(); 
		userRepository.deleteById(userObj.getUser_id());
		Optional<Tbl_lms_user> newSkill = userRepository.findById("U30");
		assertThat(newSkill).isEmpty();
	}

	@Test
	void findUserById() {
		Tbl_lms_user userObj = userRepository.findById("U01").get();
		assertThat(userObj).isNotNull();
		assertThat(userObj.getName()).isEqualTo("John");
	}


	@Test
	void findAllSkills() {
		List<Tbl_lms_user> userList = userRepository.findAll();
		if(userList!=null) {
			for (Tbl_lms_user tbl_lms_user : userList) {
				System.out.println(tbl_lms_user.getUser_id());
			}
			assertThat(userList).asList().isNotNull();
		}
	}

}
