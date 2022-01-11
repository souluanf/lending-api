package dev.luanfernandes.app.controller;

import dev.luanfernandes.app.domain.dto.LoanDto;
import dev.luanfernandes.app.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value="/loan")
public class LoanController {
	
	@Autowired
	private LoanService service;

	@GetMapping
	public ResponseEntity<List<LoanDto>> findAll() {
		var loans = service.findAll();
		return ResponseEntity.ok().body(LoanDto.of(loans));
	}
	
	@GetMapping(value="{id}")
	public ResponseEntity<LoanDto> find(@PathVariable Long id) {
		var obj = service.findById(id);
		return ResponseEntity.ok().body(new LoanDto(obj));
	}
}
