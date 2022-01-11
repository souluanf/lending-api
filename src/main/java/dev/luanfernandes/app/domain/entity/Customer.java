package dev.luanfernandes.app.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private BigDecimal income;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Profile> profiles = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    private List<Loan> loans = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone")
    private Set<String> phones = new HashSet<>();

    public Customer(String name, String email, String cpf,BigDecimal income, String password) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.income = income;
        this.password = password;
    }

    public Customer(String name, String email, String cpf,BigDecimal income, String password,List<Address> addresses) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.income = income;
        this.password = password;
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profiles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
