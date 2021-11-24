package com.nttdata.service.impl;

import com.nttdata.model.Deposit;
import com.nttdata.repository.DepositRepository;
import com.nttdata.repository.Repository;
import com.nttdata.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**Se crea la clase DepositServiceImpl extendido CrudServiceImpl implementado DepositService.*/
@Service
public class DepositServiceImpl extends CrudServiceImpl<Deposit, String> implements DepositService {

  @Autowired
  private DepositRepository repository;
  
  @Override
  protected Repository<Deposit, String> getRepository() {
    
    return repository;
    
  }

}

