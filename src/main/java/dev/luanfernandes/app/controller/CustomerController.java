package dev.luanfernandes.app.controller;

import dev.luanfernandes.app.domain.dto.CustomerDto;
import dev.luanfernandes.app.domain.entity.Customer;
import dev.luanfernandes.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService service;

	@GetMapping
	public ResponseEntity<List<CustomerDto>> findAll() {
		var customers = service.findAll();
		var customersDto = CustomerDto.of(customers);
		return ResponseEntity.ok().body(customersDto);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
		Customer obj = service.findById(id);
		return ResponseEntity.ok().body(new CustomerDto(obj));
	}
}
