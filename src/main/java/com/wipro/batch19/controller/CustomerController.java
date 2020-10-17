package com.wipro.batch19.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.batch19.beans.FundTransfer;
import com.wipro.batch19.common.NotFoundException;
import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;
import com.wipro.batch19.service.CustomerService;

@RestController
public class CustomerController {
	
	
	@Autowired
	CustomerService service;
	
	@GetMapping("/customers")
	public Iterable<Customer> getCustomers() {
		return service.getCustomers();
	}
	
	@PostMapping("/customers")
	public ResponseEntity<String> createCustomer(@RequestBody Customer cust) {
		service.createCustomer(cust);
		return ResponseEntity.ok("Customer created successfully");
	}
	
	@PutMapping("/customers/{id}")
	@ResponseBody public Customer updateCustomer(@RequestBody Customer customer, @PathVariable int id) throws NotFoundException {
		return service.updateCustomer(id, customer);
		
	}
	
	@PostMapping("/customers/transferFunds")
	public ResponseEntity<String> transferFunds(@RequestBody FundTransfer fundTransfer) throws NotFoundException {
		String responseMessage  = service.transferFunds(fundTransfer);
		return ResponseEntity.ok(responseMessage);
	}
	
	
	@PostMapping("/customers/{id}/accounts")
	@ResponseBody public ResponseEntity<Customer> createAccountForCustomer(@PathVariable int id, @RequestBody Account account) throws NotFoundException {
		Customer cust = service.updateCustomer(id, account);
		return ResponseEntity.ok(cust);

	}
	
	@GetMapping("/customers/{id}/accounts")
	public Customer getCustomerAndAccountById(@PathVariable int id ) throws NotFoundException {

		Optional<Customer> cust = service.getCustomerById(id);
		if(cust.isPresent()) {
			Customer customer = cust.get();
			return customer;
		} else {
			throw new NotFoundException("Customer with id : "+id+" is not found");
		}
		
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomerById(@PathVariable int id ) throws NotFoundException {

		Optional<Customer> cust = service.getCustomerById(id);
		if(cust.isPresent()) {
			Customer customer = cust.get();
			return customer;
		} else {
			throw new NotFoundException("Customer with id : "+id+" is not found");
		}
		
	}
}
