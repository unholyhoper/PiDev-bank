package tn.esprit.bank.entity;



import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;


import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private int accountNumber;


    private AccountType accountType;

    public BankAccount(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
