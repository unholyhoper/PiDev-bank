package tn.esprit.bank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.LoanStatus;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class LoanTranches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String amount;
    private String date;
    @Column(name = "loanStatus")
//    @Enumerated(EnumType.STRING)
    private LoanStatus loanStatus= LoanStatus.IN_PROGRESS;

    @ManyToOne
    private Loan loan;
}
