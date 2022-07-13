package tn.esprit.bank.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;


import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private int accountNumber;

    @ManyToOne
    private AccountRequest accountRequest;

    @ManyToOne
    private AbstractUser user;

    public BankAccount(int accountNumber, AbstractUser user, AccountRequest accountRequest) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.accountRequest = accountRequest;
    }
}
