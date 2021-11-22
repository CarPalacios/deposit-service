package com.nttdata.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Deposit;
import com.nttdata.repository.IDepositRepository;
import com.nttdata.repository.IRepository;
import com.nttdata.service.IDepositService;

@Service
public class DepositService extends CRUDServiceImpl<Deposit, String> implements IDepositService {

	@Autowired
	private IDepositRepository repository;
	
	@Override
	protected IRepository<Deposit, String> getRepository() {
		
		return repository;
		
	}

}

