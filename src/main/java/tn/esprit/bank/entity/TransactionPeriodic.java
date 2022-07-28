package tn.esprit.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.Periodicity;
import tn.esprit.bank.enumeration.TransactionStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class TransactionPeriodic {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private TransactionStatus status;
    private BigDecimal amount;
    private Periodicity periodicity;
    private LocalDate startDate;
    private LocalDate nextDate;

    @ManyToOne
    private BankAccount bankAccountFrom;

    @ManyToOne
    private BankAccount bankAccountTo;

}
