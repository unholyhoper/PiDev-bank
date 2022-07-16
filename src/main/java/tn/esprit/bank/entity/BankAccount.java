package tn.esprit.bank.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.bank.enumeration.AccountType;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private BigDecimal accountNumber;

    private Date initialDate;

    private float balance;

    @ManyToOne
    private AccountRequest accountRequest;

    @ManyToOne
    private AbstractUser user;

    public BankAccount(BigDecimal accountNumber, AbstractUser user, Date initialDate, float balance,AccountRequest accountRequest) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.accountRequest = accountRequest;
        this.initialDate = initialDate;
    }
}
