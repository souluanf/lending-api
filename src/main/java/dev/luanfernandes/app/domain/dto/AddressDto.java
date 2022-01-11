package dev.luanfernandes.app.domain.dto;

import dev.luanfernandes.app.domain.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;


    public AddressDto(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.neighborhood = address.getNeighborhood();
        this.postalCode = address.getPostalCode();
        this.city = address.getCity();
        this.state = address.getState();
    }

    public static List<AddressDto> of(List<Address> customers) {
        return customers.stream().map(AddressDto::new).collect(Collectors.toList());
    }
}
