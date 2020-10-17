package com.wipro.batch19.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;

@TestMethodOrder(OrderAnnotation.class)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class TestAccountRepository {
	
	@Autowired
	AccountRepository accountRepo;
	
	@Test
	@Order(2)
	public void test_getAccounts() {
		test_createAccount();
		Iterable<Account>  accounts = accountRepo.findAll();
		Iterator<Account> account = accounts.iterator();
		assertEquals(true, account.hasNext());
	}
	
	
	@Test
	@Order(1)
	public void test_createAccount() {
		
		Account acc = new Account();
		acc.setAccountNumber(1);
		acc.setAccountType("Savings");
		acc.setBalance(1000);
			

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("John");
		customer.setLastName("Kehoe");
		customer.setEmail("john@wipro.com");
		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer);
		acc.setCustomers(customers);
		
		accountRepo.save(acc);
		
	}
	
	
	public void test_getCustomer() {
		
	}

}
