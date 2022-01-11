package dev.luanfernandes.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.luanfernandes.app.domain.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
