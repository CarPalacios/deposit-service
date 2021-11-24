package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**Se crea la interfaz Repository extendido ReactiveMongoRepository.*/
@NoRepositoryBean
public interface Repository<T, K> extends ReactiveMongoRepository<T, K> {

}
