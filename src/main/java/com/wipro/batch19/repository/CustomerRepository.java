package com.wipro.batch19.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.batch19.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
