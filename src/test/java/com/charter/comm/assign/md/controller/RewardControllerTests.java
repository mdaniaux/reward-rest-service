package com.charter.comm.assign.md.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	
	//@Test
	public void getTransactioncust001StartEnd() throws Exception {

			// start Date in UTC TZ
	        this.mockMvc.perform(get("/transactions").param("customerId","cust001")
	        					.param("start","2022-01-01T05:00:00.000Z")
	        					.param("end","2022-03-31T05:00:00.000Z"))
								.andDo(print())
								//.andExpect(status().is())
								/*.andExpect(jsonPath("$.content").value("Hello, World!"))*/;


			// TODO validation work
		
	}
	
	@Test
	public void getRewardscust001StartEnd() throws Exception {

			// start Date in UTC TZ
	        this.mockMvc.perform(get("/rewards").param("customerId","cust001")
	        					.param("start","2022-01-01T05:00:00.000Z")
	        					.param("end","2022-03-31T05:00:00.000Z"))
								.andDo(print())
								//.andExpect(status().is())
								/*.andExpect(jsonPath("$.content").value("Hello, World!"))*/;


			// TODO validation work
		
	}

	//@Test
	public void getRewardscust002Past() throws Exception {

			// start Date in UTC TZ
	        this.mockMvc.perform(get("/rewards").param("customerId","cust002")
	        					.param("start","2018-01-01T05:00:00.000Z")
	        					.param("end","2018-03-31T05:00:00.000Z"))
								.andDo(print())
								.andExpect(status().isOk());


			// TODO validation work
		
	}
	
	//@Test
	public void getRewardscust001WrongStartEnd() throws Exception {

			// start Date in UTC TZ
	        this.mockMvc.perform(get("/rewards").param("customerId","cust001")
	        					.param("start","2022-04-01T05:00:00.000Z")
	        					.param("end","2022-02-31T05:00:00.000Z"))
								.andDo(print())
								.andExpect(status().is5xxServerError());


			// TODO validation work
		
	}
	
}
