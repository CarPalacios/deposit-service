package com.nttdata.service;

import com.nttdata.model.Account;

import reactor.core.publisher.Mono;

public interface IAccountService extends ICRUDService<Account, String> {
	
	Mono<Account> findByAccountNumber(String accountNumber);

}
