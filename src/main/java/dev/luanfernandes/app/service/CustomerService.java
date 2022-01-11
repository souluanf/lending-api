package dev.luanfernandes.app.service;

import dev.luanfernandes.app.domain.entity.Customer;
import dev.luanfernandes.app.repository.CustomerRepository;
import dev.luanfernandes.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;


	public List<Customer> findAll() {
		return repository.findAll();
	}
	
	public Customer findById(Long id) {
		Optional<Customer> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Customer.class.getName()));
	}

}
