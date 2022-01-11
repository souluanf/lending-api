package dev.luanfernandes.app.domain.dto;

import dev.luanfernandes.app.domain.entity.Loan;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class LoanDto {
    private Long id;
    private BigDecimal amount;
    private LocalDate firstInstallment;
    private Integer installments;
    private String customerEmail;


    public LoanDto(Loan loan) {
        this.id = loan.getId();
        this.amount = loan.getAmount();
        this.firstInstallment = loan.getFirstInstallment();
        this.installments = loan.getInstallments();
        this.customerEmail = loan.getCustomer().getEmail();
    }

    public static List<LoanDto> of(List<Loan> loans) {
        return loans.stream().map(LoanDto::new).collect(Collectors.toList());
    }

}
