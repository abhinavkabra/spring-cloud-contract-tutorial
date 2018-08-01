package io.pivotal.pivotalservices.customer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
		//test that the spring context can initialize
	}

	@Test
	public void testAddCustomer_savesCustomerToRepository() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/customers").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{ \"firstName\": \"Matt\", \"lastName\": \"Campbell\", \"email\": \"matt@example.com\" }");
		mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.status", is("OK")));
	}

}
