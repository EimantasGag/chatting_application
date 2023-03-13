package com.eimantasgag.learning_springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LearningSpringbootApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldAccessLoginPage() throws Exception {
		this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldRedirectNotLoggedInUser() throws Exception {

		//STATUS CODE 302 MEANS THE USER WAS REDIRECTED
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is(302)).andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	@WithMockUser(roles = "USER")
	public void shouldNotRedirectLoggedInUser() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk());
	}
}
