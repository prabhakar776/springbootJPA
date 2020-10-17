package com.wipro.batch19.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;
import com.wipro.batch19.service.AccountService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountsController.class)
@TestMethodOrder(OrderAnnotation.class)
public class TestAccountController {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AccountService service;

	
	@Test
	@Order(1)
	public void test_getCustomer () throws Exception {
		
		RequestBuilder request;
		
		Account acc1 = new Account();
		acc1.setAccountNumber(1);
		acc1.setAccountType("Savings");
		acc1.setBalance(100);
		acc1.setCustomers(new HashSet<Customer>());
		
		Account acc2 = new Account();
		acc2.setAccountNumber(2);
		acc2.setAccountType("Currenct");
		acc2.setBalance(100);
		acc2.setCustomers(new HashSet<Customer>());
		
		
		
		Customer cust2 = new Customer();
		cust2.setCustomerId(2);
		cust2.setFirstName("Prabha");
		cust2.setLastName("anab");
		cust2.setEmail("prabaar@wipro.com");
		cust2.setAccounts(new HashSet<Account>());
		
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(acc1);
		accounts.add(acc2);
		
		System.out.println(accounts);
		
		String exptResult = "[Account [accountNumber = 1 , accountType = Savings , balance = 100.0 ], Account [accountNumber = 2 , accountType = Currenct , balance = 100.0 ]]";
		
		
		when(service.getAccounts()).thenReturn(accounts);
		
		request = MockMvcRequestBuilders
				.get("/accounts")
				.accept(MediaType.APPLICATION_JSON);
		
		
		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andReturn();
	}
}
