package com.nttdata.controller;

import com.nttdata.model.Deposit;
import com.nttdata.service.DepositService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**Dentro de la clase DepositController se realizan los metodos http.*/
@RestController
@RequestMapping("/deposit")
public class DepositController {

  @Autowired
  private DepositService service;
  
  /** Muestra los registros de la tabla * @return registro de la tabla seleccionada. */
  @GetMapping
  public Mono<ResponseEntity<Flux<Deposit>>> findAll() {
    
    Flux<Deposit> deposit = service.findAll();
    
    return Mono.just(ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(deposit));              
    
  }
  
  /** Muestra los registros de la tabla a traves de un id.
   * * @return registro de la tabla seleccionada por id. */
  @GetMapping("/{id}")
  public Mono<ResponseEntity<Deposit>> findById(@PathVariable("id") String id) {
    
    return service.findById(id)
        .map(objectFound -> ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(objectFound))
        .defaultIfEmpty(ResponseEntity
            .noContent()
            .build());
            
  }
  
  /** Crea los registros de la tabla.* @return crea registros de la tabla. */
  @PostMapping
  public Mono<ResponseEntity<Deposit>> 
      create(@RequestBody Deposit deposit, final ServerHttpRequest request) {
    
    return service.create(deposit)
        .map(c -> ResponseEntity
            .created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
            .contentType(MediaType.APPLICATION_JSON)
            .body(c));
  }
  
}