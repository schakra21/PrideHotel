package com.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.model.Customer;

public class CustomerControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void retrieveAllCustomers() throws Exception {
		createCustomer();
		String uri = "/customers";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Customer[] customersList = super.mapFromJson(content, Customer[].class);
		assertTrue(customersList.length > 0);
	}
	 @Test
	   public void createCustomer() throws Exception {
	      String uri = "/customers";
	      Customer customer = new Customer();
	      customer.setCustomerName("Alex");
	      
	      
	      String inputJson = super.mapToJson(customer);
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	         .contentType(MediaType.APPLICATION_JSON_VALUE)
	         .content(inputJson)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(201, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      assertEquals(content, "Customer is created successfully");
	   }
}
