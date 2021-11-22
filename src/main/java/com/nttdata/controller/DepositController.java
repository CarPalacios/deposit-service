package com.nttdata.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Deposit;
import com.nttdata.service.IDepositService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/deposit")
public class DepositController {

	@Autowired
	private IDepositService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<Deposit>>> findAll() {
		
		Flux<Deposit> deposit = service.findAll();
		
		return Mono.just(ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(deposit));							
		
	}
	
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
	
	@PostMapping
	public Mono<ResponseEntity<Deposit>> create(@RequestBody Deposit deposit, final ServerHttpRequest request) {
		
		return service.create(deposit)
				.map(c -> ResponseEntity
						.created(URI.create(request.getURI().toString().concat("/").concat(c.getId())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(c));
	}
	
}