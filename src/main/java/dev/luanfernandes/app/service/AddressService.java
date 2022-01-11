package dev.luanfernandes.app.service;

import dev.luanfernandes.app.domain.entity.Address;
import dev.luanfernandes.app.repository.AddressRepository;
import dev.luanfernandes.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

	@Autowired
	private AddressRepository repository;


	public List<Address> findAll() {
		return repository.findAll();
	}

	public Address findById(Long id) {
		Optional<Address> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Address.class.getName()));
	}
}
