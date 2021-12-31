package com.lms.api.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.api.demo.DTO.UserInfoDTO;
import com.lms.api.demo.controller.LMSUserController;
import com.lms.api.demo.entity.Tbl_lms_user;
import com.lms.api.demo.entity.converter.LMSUserEntityConverter;
import com.lms.api.demo.jpa.repository.UserRepository;
import com.lms.api.demo.service.UserDAOService;
import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(LMSUserController.class)
class LmsApiDemoApplicationTestsMockito {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserDAOService service; 

	//@MockBean
	//private UserRepository userRepository;
	private UserRepository userRepository = Mockito.mock(UserRepository.class);

	@MockBean 
	LMSUserEntityConverter converter;

	@MockBean
	UserInfoDTO userInfoDTO;

	@MockBean
	JSONObject responseMsg;

	@Test
	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	public void createLoads() throws Exception {
		Mockito.when(service.findAll())
		.thenReturn(Stream.of(new Tbl_lms_user("U01", "Matt","Paul",12345678910L, 
				"New Jersey", "EST", "https://www.linkedin.com/in/MattPaul/",
				"BA","MS", "", 
				"US-Citizen", LocalDateTime.now(), LocalDateTime.now()))
				.collect(Collectors.toList()));
		mockMvc.perform(MockMvcRequestBuilders.get("/Users")
				.accept(MediaType.APPLICATION_JSON))

		.andDo(print()).andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].user_id").value("U01"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Matt"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname").value("Paul"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].phone_number").value(12345678910L))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("New Jersey"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].time_zone").value("EST"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].linkedin_url").value("https://www.linkedin.com/in/MattPaul/"));
	}

	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	@Test
	public void findUserById() throws Exception {
		Tbl_lms_user user = new Tbl_lms_user("U03", "Maria","Poppins",9562867512L, 
				"Pittsburgh", "EST", "https://www.linkedin.com/in/MaryPoppins/",
				"Information Technology","Computer Science Engineering", "", 
				"GC-EAD",LocalDateTime.now(), LocalDateTime.now());
		Mockito.when(service.finOneUser("U03")).thenReturn(user);
		mockMvc.perform(get("/Users/U03")).andExpect(status().isOk());
	}

	//negative scenario
	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	@Test 
	public void findAInvalidUserByID() throws Exception {
		Mockito.when(service.finOneUser("U10")).thenReturn(null);
		mockMvc.perform(get("/Users/U10")).andExpect(status().isNotFound());

	}
	
	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	@Test
	public void deleteUser() throws Exception {
		responseMsg.put("U22", "User Successfully Deleted");
		userInfoDTO.setMessage(responseMsg);
		Mockito.when(service.deleteUser("U22")).thenReturn(userInfoDTO);
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/User/U22").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

	}

	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	@Test
	void createUser() throws Exception {
		responseMsg.put("U16", "User Successfully Created");
		userInfoDTO.setMessage(responseMsg);
		Tbl_lms_user user = new Tbl_lms_user("U16", "Bryce","Paul",12345678910L, 
				"New Jersey", "EST", "https://www.linkedin.com/in/BrycePaul/",
				"BS","MS", "", 
				"US-Citizen", LocalDateTime.now(), LocalDateTime.now());
		//userRepository.saveAndFlush(user);
		Mockito.when(service.createUser(user)).thenReturn(userInfoDTO);

		String userDTOString = new ObjectMapper().writeValueAsString(user);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/Users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userDTOString)
				.characterEncoding("utf-8"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(content().json(userDTOString));
		/*this.mockMvc.perform(MockMvcRequestBuilders.post("/Users").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));*/

	}

	@WithMockUser(username = "APIPROCESSING", password = "2xx@Success")
	@Test
	void updateUser() throws Exception {
		LocalDateTime creationTime = LocalDateTime.of(2021, 04, 10, 18, 9, 38, 076245);   
		Tbl_lms_user user = new Tbl_lms_user("U03", "Maria","Poppins",9562867512L, 
				"Pittsburgh", "EST", "https://www.linkedin.com/in/MaryPoppins/",
				"Information Technology","Computer Science Engineering", "", 
				"GC-EAD",creationTime, LocalDateTime.now());
		Mockito.when(service.finOneUser("U03")).thenReturn(user);
		user.setName("Mary");
		user.setLast_mod_time(LocalDateTime.now());
		responseMsg.put("U03", "User Successfully Updated");
		userInfoDTO.setMessage(responseMsg);
		Mockito.when(service.updateUser(user)).thenReturn(userInfoDTO);
		//mockMvc.perform(put("/Users/U03")).andExpect(status().isOk());
		String userDTOString = new ObjectMapper().writeValueAsString(user);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/Users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userDTOString)
				.characterEncoding("utf-8"))
		.andDo(print())
		.andExpect(status().isOk());
		/*this.mockMvc.perform(MockMvcRequestBuilders.put("/Users/U03").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));*/
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
