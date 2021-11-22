package com.nttdata.repository;

import com.nttdata.model.Account;

import reactor.core.publisher.Mono;

public interface IAccountRepository extends IRepository<Account, String> {

	Mono<Account> findByAccountNumber(String accountNumber);
	
}
