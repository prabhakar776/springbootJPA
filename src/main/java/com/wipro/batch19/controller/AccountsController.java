package com.wipro.batch19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.batch19.entity.Account;
import com.wipro.batch19.service.AccountService;


@RestController
public class AccountsController {
	
	@Autowired
	AccountService service;
	
	@GetMapping("/accounts")
	public Iterable<Account> getCustomers() {
		return service.getAccounts();
	}
	
	

}
