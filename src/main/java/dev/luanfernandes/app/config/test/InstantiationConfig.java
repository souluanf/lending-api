package dev.luanfernandes.app.config.test;

import dev.luanfernandes.app.domain.entity.*;
import dev.luanfernandes.app.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
@Slf4j
public class InstantiationConfig implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void run(String... args) {

        Customer customer1 = new Customer("Maria Silva", "maria@maria.com", "36378912377",new BigDecimal("1300"), new BCryptPasswordEncoder().encode("12345"));
        Customer customer2 = new Customer("Luan Fernandes", "luan@luan.com", "36378912377",new BigDecimal("2800"),new BCryptPasswordEncoder().encode("12345"));

        customer1.getPhones().addAll(List.of("27363323", "93838393"));
        customer2.getPhones().addAll(List.of("32657898", "985257412"));

        Address address1 = new Address("Rua Flores", "300", "Apto 303", "Jardim", "38220834","Minas Gerais" ,"Minas Gerais",customer1);
        Address address2 = new Address("Rua Senador Georgino", "647", "Ap 224", "Itaquera", "08295370","São Paulo","São Paulo", customer2);

        customer1.getAddresses().addAll(List.of(address1));
        customer2.getAddresses().addAll(List.of(address2));

        customerRepository.saveAll(List.of(customer1, customer2));
        addressRepository.saveAll(List.of(address1, address2));

        Loan loan1 = new Loan(new BigDecimal("500"), LocalDate.of(2022, Month.FEBRUARY,7),10,customer2);
        loanRepository.saveAll(List.of(loan1));
    }
}
