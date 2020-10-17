package com.wipro.batch19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.batch19.entity.Account;
import com.wipro.batch19.repository.AccountRepository;

@Service
public class AccountService {
	
	
	@Autowired
	AccountRepository repository;
	
	public Iterable<Account> getAccounts() {
		return repository.findAll();
	}
	
	
	
	
}
