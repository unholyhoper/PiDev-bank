package tn.esprit.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double amount;
    private String interest;
    private String duration ;
    private String payment;

    @ManyToOne
    private BankAccount bankAccount;

}
