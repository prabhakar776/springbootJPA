package com.wipro.batch19.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.batch19.beans.FundTransfer;
import com.wipro.batch19.common.NotFoundException;
import com.wipro.batch19.entity.Account;
import com.wipro.batch19.entity.Customer;
import com.wipro.batch19.repository.AccountRepository;
import com.wipro.batch19.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;
	
	
	@Autowired
	AccountRepository accountRepo;
	
	
	public Customer updateCustomer(int id, Customer customer) throws NotFoundException {
		
		Optional<Customer> customerOpt =  custRepo.findById(id);
		if(customerOpt.isPresent()) {
			Customer cust = customerOpt.get();
			cust.setFirstName(customer.getFirstName());
			cust.setLastName(customer.getLastName());
			cust.setEmail(customer.getEmail());
			custRepo.save(cust);
			return cust;
			
		} else {
			throw new NotFoundException("Customer with id "+id+" does not exist");
		}
		
	}
	
	
	
	public String transferFunds(FundTransfer fundTransfer) throws NotFoundException {
		
		String returnMessage = "Funds transferred success";
		
		if(fundTransfer.getFromAccount() != fundTransfer.getToAccount()) {
			Optional<Account> fromOptAccount =  accountRepo.findById(fundTransfer.getFromAccount());
			Optional<Account> toOptAccount =  accountRepo.findById(fundTransfer.getToAccount());
			if (fromOptAccount.isPresent() && toOptAccount.isPresent() ) {
				Account  fromAccount = fromOptAccount.get();
				double balance = fromAccount.getBalance();
				if(balance >= fundTransfer.getAmount()) {
					fromAccount.setBalance( balance - fundTransfer.getAmount());
					Account toAccount = toOptAccount.get();
					toAccount.setBalance(toAccount.getBalance()+ fundTransfer.getAmount());
					
					accountRepo.save(fromAccount);
					accountRepo.save(toAccount); } 
				else {
						returnMessage ="insufficient funds";
					}
				
			} else {
				throw new NotFoundException("Account number does not exist");
			}
			
		} else {
			returnMessage ="From account and to accout can't be same";
		}
		
		return returnMessage;
		
	}
	
	
	public void createCustomer(Customer customer) {
		custRepo.save(customer);
	}
	
	@Transactional
	public Iterable<Customer> getCustomers() {
		
		return  custRepo.findAll();

	}
	
	public Optional<Customer> getCustomerById(int id) {
		
		return	custRepo.findById(id);
	}
	
	public Customer updateCustomer(int id, Account account) throws NotFoundException {
		
		Optional<Customer> cust = custRepo.findById(id);
		if(cust.isPresent()) {
			
			Customer customer = cust.get();
			Set<Account> accounts = customer.getAccounts();
			accounts.add(account);
			customer.setAccounts(accounts);
			
			Set<Customer> customers = new HashSet<Customer>();
			customers.add(customer);
			account.setCustomers(customers);
			custRepo.save(customer);
			
			return customer;
		} else {
			throw new NotFoundException("Customer with id : "+id+" is not found");
		}
	}
	
	public Customer createAccountForCustomer(Customer customer, Account account) {
		
		
		Set<Account> accounts = customer.getAccounts();
		accounts.add(account);
		customer.setAccounts(accounts);
		
		Set<Customer> customers = new HashSet<Customer>();
		customers.add(customer);
		
		account.setCustomers(customers);
		custRepo.save(customer);
		
		return customer;
	}

}
