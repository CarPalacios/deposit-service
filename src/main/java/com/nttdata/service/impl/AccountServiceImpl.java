package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Account;
import com.nttdata.repository.IAccountRepository;
import com.nttdata.repository.IRepository;
import com.nttdata.service.IAccountService;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl extends CRUDServiceImpl<Account, String> implements IAccountService {
	
	@Autowired
	private IAccountRepository repository;
	
	@Override
	protected IRepository<Account, String> getRepository() {
		
		return repository;
		
	}

	@Override
	public Mono<Account> findByAccountNumber(String accountNumber) {
		
		return repository.findByAccountNumber(accountNumber);
		
	}

}