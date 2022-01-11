package dev.luanfernandes.app.config.security;

import dev.luanfernandes.app.domain.entity.Customer;
import dev.luanfernandes.app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = repository.findByEmail(username);
        return customer.orElseThrow(() -> new UsernameNotFoundException("Invalid customer data"));
    }
}
