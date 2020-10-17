package com.wipro.batch19.repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.batch19.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer>{

}
