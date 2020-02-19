package com.test.xbrainorder;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.test.xbrainorder.controller.OrderController;


public class OrderControllerTest extends XbrainorderApplicationTests{
	
	private MockMvc mockMvc;
	
	@Autowired
	private OrderController OrderController;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(OrderController).build();
	}
	
	@Test
	public void testGETIndexOrderController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/orders")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
