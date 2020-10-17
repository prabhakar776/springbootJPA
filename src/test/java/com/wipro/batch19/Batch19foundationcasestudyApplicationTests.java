package com.wipro.batch19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class Batch19foundationcasestudyApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	@Order(3)
	public void test_createCustomer() {
		
		Customer cust = new Customer();
		cust.setFirstName("Anu");
		cust.setLastName("S");
		cust.setEmail("fiona@wipro.com");
		cust.setAccounts(new HashSet<Account>());
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("content-type", "application/json");
		
		HttpEntity<Customer> request = new HttpEntity<>(cust, headers);
		ResponseEntity<String>  result = this.restTemplate.postForEntity("/customers", request, String.class);
		assertEquals(200, result.getStatusCodeValue());
		
	}
	
	
	@Test
	@Order(1)
	public void test_getCustoemers() {
		Iterable<Customer> cust = restTemplate.getForObject("/customers", List.class);
		Iterator<Customer> iterator = cust.iterator();
		assertEquals(true, iterator.hasNext());
		
	}
	
	@Test
	@Order(2)
	public void test_getAccounts() {
		Iterable<Account> cust = restTemplate.getForObject("/accounts", List.class);
		Iterator<Account> iterator = cust.iterator();
		assertEquals(true, iterator.hasNext());
		
	}

}
