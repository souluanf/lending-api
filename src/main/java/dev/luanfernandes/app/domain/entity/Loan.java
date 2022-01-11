package dev.luanfernandes.app.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Loan implements Serializable {
    private static final long serialVersionUID = 2636364601840363431L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstInstallment;
    private Integer installments;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Loan(BigDecimal amount, LocalDate firstInstallment, Integer installments, Customer customer) {
        this.amount = amount;
        this.firstInstallment = firstPaymentValidation(firstInstallment);
        this.installments = instalmentsValidation(installments);
        this.customer = customer;
    }

    public void setInstallments(Integer installments) {
        this.installments = instalmentsValidation(installments);
    }

    private Integer instalmentsValidation(Integer installments) {
        if (installments >= 1 && installments <= 60) {
            return installments;
        } else throw new IllegalArgumentException("purchase needs to be between 1 and 60 installment");
    }

    public void setFirstInstallment(LocalDate firstInstallment) {
        this.firstInstallment = firstPaymentValidation(firstInstallment);
    }

    private LocalDate firstPaymentValidation(LocalDate firstInstallment) {
        long months = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.now()), YearMonth.from(firstInstallment));
        if (months <= 3) {
            return firstInstallment;
        } else throw new IllegalArgumentException("the date of the first payment must be no later than 3 months after the current date");
    }
}
