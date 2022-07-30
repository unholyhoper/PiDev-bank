package tn.esprit.bank.entity;



import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import tn.esprit.bank.enumeration.AccountType;


import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String type;

    private BigInteger accountNumber;

    private Date initialDate;

    private BigDecimal balance;

    @ManyToOne
    private AccountRequest accountRequest;

    @ManyToOne
    private AbstractUser user;

    public BankAccount(BigInteger accountNumber, AbstractUser user, Date initialDate, BigDecimal balance,AccountRequest accountRequest) {
        this.accountNumber = accountNumber;
        this.user = user;
        this.balance = balance;
        this.accountRequest = accountRequest;
        this.initialDate = initialDate;
    }
}
