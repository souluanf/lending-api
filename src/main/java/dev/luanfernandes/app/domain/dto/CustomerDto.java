package dev.luanfernandes.app.domain.dto;

import dev.luanfernandes.app.domain.entity.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
public class CustomerDto {
    private String name;
    private String email;
    private String cpf;
    private BigDecimal income;
    private Set<String> phones;
    private List<AddressDto> address;


    public CustomerDto(Customer customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.cpf = customer.getCpf();
        this.income = customer.getIncome();
        this.phones = customer.getPhones();
        this.address = AddressDto.of(customer.getAddresses());
    }

    public static List<CustomerDto> of(List<Customer> customers) {
        return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
    }

}
