package com.wipro.batch19.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private int accountNumber;
	private String accountType;
	private double balance;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "customer_accounts",
			joinColumns = @JoinColumn(name="account_id"),
			inverseJoinColumns = @JoinColumn(name ="customer_id")
			)
	@JsonIgnoreProperties(value = {"accounts"})
	private Set<Customer> customers;

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber = "+accountNumber +" , accountType = "+accountType+" , balance = "+balance+" ]";
	}
	

}
