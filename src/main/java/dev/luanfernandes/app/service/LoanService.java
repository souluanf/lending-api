package dev.luanfernandes.app.service;

import dev.luanfernandes.app.domain.entity.Loan;
import dev.luanfernandes.app.repository.LoanRepository;
import dev.luanfernandes.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
	
	@Autowired
	private LoanRepository repository;


	public List<Loan> findAll() {
		return repository.findAll();
	}
	
	public Loan findById(Long id) {
		Optional<Loan> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Loan.class.getName()));
	}

}
