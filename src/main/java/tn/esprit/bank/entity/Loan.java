package tn.esprit.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.LoanStatus;

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
    private Integer duration;
    private String payment;
    @Column(name = "loanStatus")
    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus;

    @ManyToOne
    private CurrentAccount bankAccount;

}
