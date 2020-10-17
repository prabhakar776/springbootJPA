package com.wipro.batch19.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wipro.batch19.beans.FundTransfer;
import com.wipro.batch19.common.NotFoundException;
import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;
import com.wipro.batch19.repository.AccountRepository;
import com.wipro.batch19.repository.CustomerRepository;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
public class TestCustomerService {
	
	@InjectMocks
	CustomerService service;
	
	@Mock
	CustomerRepository repository;
	
	@Mock
	AccountRepository accRepo;
	
	@Test
	@Order(4)
	public void test_updateCustomerAcc() throws NotFoundException {
		
		Customer customer = new Customer();
		customer.setFirstName("prabhakar");
		customer.setLastName("Amb");
		customer.setEmail("prab@wipro.com");
		customer.setCustomerId(1);
		customer.setAccounts(new HashSet<Account>());
		
		Optional<Customer> custo = Optional.of(customer);
		
		when(repository.findById(1)).thenReturn(custo);
		
		Account account = new Account();
		account.setAccountType("Saving");
		account.setBalance(1000);
		
		Customer updatedCustomer =service.updateCustomer(1, account);
		assertEquals(1, updatedCustomer.getAccounts().size());
	
	}
	
	
	
	@Test
	@Order(3)
	public void test_transferFunds() throws NotFoundException {
		FundTransfer fundTran = new FundTransfer();
		fundTran.setFromAccount(1);
		fundTran.setToAccount(2);
		fundTran.setAmount(100);
		
		Account acc1 = new Account();
		acc1.setAccountType("Savings");
		acc1.setAccountNumber(1);
		acc1.setBalance(1000);
		Optional<Account> fromAcc = Optional.of(acc1);
		
		Account acc2 = new Account();
		acc2.setAccountType("Current");
		acc2.setAccountNumber(2);
		acc2.setBalance(0);
		Optional<Account> toAcc = Optional.of(acc2);
		
		when(accRepo.findById(1)).thenReturn(fromAcc);
		when(accRepo.findById(2)).thenReturn(toAcc);
		
		String message = service.transferFunds(fundTran);
		
		assertEquals("Funds transferred success", message);
		
		
		
	}
	
	@Test
	@Order(2)
	public void test_updateCustomer() throws NotFoundException {
		
		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setFirstName("prabhakar");
		customer.setLastName("Amb");
		customer.setEmail("prab@wipro.com");
		customer.setAccounts(new HashSet<Account>());
		
		Optional<Customer>  cust = Optional.of(customer);
		when(repository.findById(1)).thenReturn(cust);
		
		Customer custome = service.updateCustomer(1, customer);
		assertEquals(1, custome.getCustomerId());
	}
	
	
	@Test
	@Order(1)
	public void test_createAccountForCustomer() {
		
		Customer customer = new Customer();
		customer.setFirstName("prabhakar");
		customer.setLastName("Amb");
		customer.setEmail("prab@wipro.com");
		
		customer.setAccounts(new HashSet<Account>());
		
		Account account = new Account();
		account.setAccountType("Saving");
		account.setBalance(1000);
		
		Customer updatedCustomer = service.createAccountForCustomer(customer, account);
		
		assertEquals(1, updatedCustomer.getAccounts().size());
		
		
	}
	

}
