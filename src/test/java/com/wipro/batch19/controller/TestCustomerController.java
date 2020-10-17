package com.wipro.batch19.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;
import com.wipro.batch19.service.CustomerService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
@TestMethodOrder(OrderAnnotation.class)
public class TestCustomerController {
	

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;
	
	public void test_updateCustomer() throws Exception {
		
RequestBuilder request;
		
		Customer cust1 = new Customer();
		cust1.setCustomerId(1);
		cust1.setFirstName("John");
		cust1.setLastName("Kehoe");
		cust1.setEmail("john@wipro.com");
		cust1.setAccounts(new HashSet<Account>());
		Optional<Customer> customer = Optional.of(cust1);
		
		when(service.getCustomerById(1)).thenReturn(customer);
		
		request = MockMvcRequestBuilders
				.put("/customers/{id}",1)
				.content(asJsonString(cust1))
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andReturn();
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	@Test
	@Order(2)
	public void test_getCustomerByid() throws Exception {
		
		RequestBuilder request;
		
		Customer cust1 = new Customer();
		cust1.setCustomerId(1);
		cust1.setFirstName("John");
		cust1.setLastName("Kehoe");
		cust1.setEmail("john@wipro.com");
		cust1.setAccounts(new HashSet<Account>());
		Optional<Customer> customer = Optional.of(cust1);
		
		when(service.getCustomerById(1)).thenReturn(customer);
		request = MockMvcRequestBuilders
				.get("/customers/{id}", 1)
				.accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andReturn();
	}
	
	@Test
	@Order(1)
	public void test_getCustomer () throws Exception {
		
		RequestBuilder request;
		
		Customer cust1 = new Customer();
		cust1.setCustomerId(1);
		cust1.setFirstName("John");
		cust1.setLastName("Kehoe");
		cust1.setEmail("john@wipro.com");
		cust1.setAccounts(new HashSet<Account>());
		
		
		Customer cust2 = new Customer();
		cust2.setCustomerId(2);
		cust2.setFirstName("Prabha");
		cust2.setLastName("anab");
		cust2.setEmail("prabaar@wipro.com");
		cust2.setAccounts(new HashSet<Account>());
		
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(cust1);
		customers.add(cust2);
		
		when(service.getCustomers()).thenReturn(customers);
		
		request = MockMvcRequestBuilders
				.get("/customers")
				.accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andReturn();
	}
}
