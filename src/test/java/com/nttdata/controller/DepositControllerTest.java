package com.nttdata.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.nttdata.model.Deposit;
import com.nttdata.repository.DepositRepository;
import com.nttdata.service.impl.DepositServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Import(DepositServiceImpl.class)
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = DepositController.class, excludeAutoConfiguration = {ReactiveSecurityAutoConfiguration.class})

class DepositControllerTest {
  
  @Autowired
  private WebTestClient deposit;
  
  @MockBean
  private DepositRepository repository;

  @Test
  void testFindAll() {
    Deposit deposits = new Deposit("1","45518052352497921",60.0,"Primer Deposito",LocalDateTime.now());
    List<Deposit> list = new ArrayList<>();
    list.add(deposits);
    Flux<Deposit> fluxdeposit = Flux.fromIterable(list);
    
    Mockito.when(repository.findAll()).thenReturn(fluxdeposit);
    deposit.get().uri("/deposit").accept(MediaType.APPLICATION_JSON)
    .exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBodyList(Deposit.class).hasSize(1);
  }

  @Test
  void testFindById() {
    
    Deposit deposits = new Deposit("1","45518052352497921",60.0,"Primer Deposito",LocalDateTime.now());
    
    Mockito.when(repository.findById("1")).thenReturn(Mono.just(deposits));
    deposit.get().uri("/deposit/1")
    .accept(MediaType.APPLICATION_JSON).exchange()
    .expectStatus().isOk()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("45518052352497921")
    .jsonPath("$.amount").isEqualTo(60.0)
    .jsonPath("$.description").isEqualTo("Primer Deposito");
    
  }

  @Test
  void testCreate() {
    Deposit deposits = new Deposit("2","45518052352497032",120.0,"Segundo Deposito",LocalDateTime.now());
    
    Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(Mono.just(deposits));
    deposit.post().uri("/deposit")
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON)
    .body(Mono.just(deposits), Deposit.class)
    .exchange()
    .expectStatus().isCreated()
    .expectHeader().contentType(MediaType.APPLICATION_JSON)
    .expectBody()
    .jsonPath("$.id").isNotEmpty()
    .jsonPath("$.cardNumber").isEqualTo("45518052352497032")
    .jsonPath("$.amount").isEqualTo(120.0)
    .jsonPath("$.description").isEqualTo("Segundo Deposito");
    
  }

}
